package com.example.humanressapp.Controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepCreateDto {
    // step oluşturulurken bunlar kullanılır.
    private String stepName;
    private int sOrder;
    private long jobAdvertisementId;
    // hangi iş ilanına step açıldığı
}
