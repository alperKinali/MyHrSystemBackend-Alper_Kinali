package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.City;
import com.example.humanressapp.model.HR;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Builder
public class JobAdvertisementDto {
    private long id;
    private String title;
    private String description;
    private City city;
    private HR hr;
    private List<Candidate> candidates;



}
