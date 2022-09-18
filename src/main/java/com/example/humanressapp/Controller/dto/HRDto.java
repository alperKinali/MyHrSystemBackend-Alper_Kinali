package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.HR;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HRDto {
    private long id;
    private String companyName;
    private String webSite;
    private  String adress;
    private String email;
    // Hr ile ilgili tüm alanlar


}
