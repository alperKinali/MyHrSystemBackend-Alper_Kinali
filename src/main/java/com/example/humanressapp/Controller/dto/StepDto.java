package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.Interview;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StepDto {
    private  long id;
    private String stepName;
    private int sOrder;
    private List<Interview> interviews;


}
