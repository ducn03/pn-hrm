package com.premiernuts.hrm.core.aop;

import com.premiernuts.hrm.core.constants.SecurityConstants;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.security.JwtHelper;
import com.premiernuts.hrm.core.security.UserContext;
import com.premiernuts.hrm.core.security.UserPrincipal;
import com.premiernuts.hrm.core.utils.DateTimeHelper;
import com.premiernuts.hrm.core.utils.StringHelper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Slf4j
@Aspect
@Order(1)
@Component
public class LogActivityAspect {

    private final JwtHelper jwtHelper;

    public LogActivityAspect(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Around("@annotation(logActivity)")
    public Object handle(ProceedingJoinPoint point, LogActivity logActivity) throws Throwable {
        long startTime = DateTimeHelper.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }

        String path = "UNKNOWN";
        String method = "UNKNOWN";
        if (request != null) {
            path = request.getRequestURI();
            method = request.getMethod();
        }

        try {
            if (logActivity.requireAuth() && request != null) {
                authorizeRequest(request, logActivity.permissions());
            }

            Object result = point.proceed();
            long duration = DateTimeHelper.currentTimeMillis() - startTime;

            UserPrincipal current = UserContext.get();
            String userTag = "ANONYMOUS";
            if (current != null && current.getUsername() != null) {
                userTag = current.getUsername();
            }

            // Ghi log hiá»‡u nÄƒng nháº¹, KHÃ”NG serialize toÃ n bá»™ body response Ä‘á»ƒ trÃ¡nh memory heap spike
            log.info("[LogActivity] [{} {}] duration={}ms | user={}", method, path, duration, userTag);

            return result;
        } catch (PnException e) {
            log.warn("[LogActivity] [{} {}] PnException: [code={}] {}", method, path, e.getErrorCode(), e.getErrorMessage());
            throw e;
        } catch (Throwable t) {
            log.error("[LogActivity] [{} {}] Unhandled error: {}", method, path, t.getMessage());
            throw t;
        } finally {
            UserContext.clear();
        }
    }

    private void authorizeRequest(HttpServletRequest request, String[] requiredPermissions) {
        String authHeader = request.getHeader(SecurityConstants.HEADER_AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {
            throw new PnException(ErrorCodes.UNAUTHORIZED.UNAUTHORIZED);
        }

        String token = StringHelper.trim(authHeader.substring(SecurityConstants.BEARER_PREFIX.length()));
        try {
            // Tá»‘i Æ°u Ä‘Æ¡n lÆ°á»£t (Single-pass parsing): TrÃ­ch xuáº¥t UserPrincipal 1 láº§n duy nháº¥t thay vÃ¬ parse claims 5 láº§n
            UserPrincipal principal = jwtHelper.extractPrincipal(token);

            if (principal == null || principal.getUserId() == null || principal.getUsername() == null) {
                throw new PnException(ErrorCodes.UNAUTHORIZED.TOKEN_INVALID);
            }

            // Kiá»ƒm tra phÃ¢n quyá»n hoÃ n toÃ n báº±ng PBAC (Permission-Based Access Control)
            if (requiredPermissions != null && requiredPermissions.length > 0) {
                boolean hasPerm = false;
                List<String> permissions = principal.getPermissions();
                if (permissions != null) {
                    for (String reqPerm : requiredPermissions) {
                        if (reqPerm != null && (permissions.contains("*") || permissions.contains(reqPerm))) {
                            hasPerm = true;
                            break;
                        }
                    }
                }
                if (!hasPerm) {
                    throw new PnException(ErrorCodes.FORBIDDEN.ACCESS_DENIED);
                }
            }

            UserContext.set(principal);
            request.setAttribute(SecurityConstants.REQ_ATTR_USER_PRINCIPAL, principal);
            request.setAttribute(SecurityConstants.REQ_ATTR_USER_ID, principal.getUserId());
        } catch (ExpiredJwtException e) {
            throw new PnException(ErrorCodes.UNAUTHORIZED.TOKEN_EXPIRED);
        } catch (PnException e) {
            throw e;
        } catch (Exception e) {
            throw new PnException(ErrorCodes.UNAUTHORIZED.TOKEN_INVALID);
        }
    }
}
