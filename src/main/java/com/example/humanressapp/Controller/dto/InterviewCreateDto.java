package com.example.humanressapp.Controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewCreateDto {
    private boolean result;
    private String notes;
    private Long candidateId;
    private long stepId;

    //sadece Interview Olusturulacakken kullanılacak alanlar.
}
