package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.Step;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterviewDto {
    private long id;
    private boolean result;
    private String notes;
    private CandidateDto candidateDto;
    private Step step;
    // bütün mülakat kolonları.
}
