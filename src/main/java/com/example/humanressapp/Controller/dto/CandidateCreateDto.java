package com.example.humanressapp.Controller.dto;

import com.example.humanressapp.model.Candidate;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
public class CandidateCreateDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private  String email;
    private  String password;

    public Candidate toCandidate() {

        Candidate candidate = new Candidate();
        candidate.setFirstName(this.firstName);
        candidate.setLastName(this.lastName);
        candidate.setBirthDate(this.birthDate);
        candidate.setPhoneNumber(this.phoneNumber);
        candidate.setEmail(this.email);
        candidate.setPassword(this.password);
        return  candidate;

        /*return Candidate.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthDate(this.birthDate)
                .phoneNumber(this.phoneNumber)

                .build();*/
    }
}
