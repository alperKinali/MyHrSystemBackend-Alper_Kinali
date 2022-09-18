package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode
public class HRCreateDto {
    private String companyName;
    private String webSite;
    private  String adress;
    private String email;
    private String password;

    public HR toHR() {
        HR humanRes= new HR();
        humanRes.setCompanyName(this.companyName);
        humanRes.setWebSite(this.webSite);
        humanRes.setAdress(this.adress);
        humanRes.setEmail(this.email);
        humanRes.setPassword(this.password);
        return  humanRes;

        /*return HR.builder()
                .companyName(this.companyName)
                .webSite(this.webSite)
                .adress(this.adress)
                .build();*/
    }
}
