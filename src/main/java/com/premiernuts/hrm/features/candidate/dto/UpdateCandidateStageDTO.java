package com.premiernuts.hrm.features.candidate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCandidateStageDTO {

    private String stage;
    private String interviewNote;
    private Integer interviewScore;
}
