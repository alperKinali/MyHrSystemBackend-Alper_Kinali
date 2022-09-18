package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobAdvertisementCreateDto {
    private String title;
    private String description;
    private int cityId;
    private Long hrId;
}
