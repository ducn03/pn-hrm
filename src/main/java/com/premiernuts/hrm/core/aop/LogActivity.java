package com.premiernuts.hrm.core.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogActivity {

    /**
     * Báº¯t buá»™c pháº£i cÃ³ Authorization: Bearer <token>
     */
    boolean requireAuth() default true;

    /**
     * Danh sÃ¡ch quyá»n háº¡n Ä‘Æ°á»£c phÃ©p truy cáº­p (náº¿u Ä‘á»ƒ trá»‘ng = khÃ´ng yÃªu cáº§u quyá»n cá»¥ thá»ƒ)
     */
    String[] permissions() default {};
}
