package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.CandidateCreateDto;
import com.example.humanressapp.Controller.dto.CandidateDto;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.CandidateService;
import com.example.humanressapp.service.abstracts.InterviewService;
import com.example.humanressapp.service.abstracts.JobAdvertisementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;
    @Mock
    private JobAdvertisementService jobAdvertisementService;
    @Mock
    private InterviewService interviewService;

    @InjectMocks
    private CandidateController underTest;

    @Test
    void  whenCandidateCreate(){
        CandidateCreateDto candidateCreateDto = new CandidateCreateDto();
        String firstName="firstName";
        candidateCreateDto.setFirstName(firstName);
        String lastName="lastName";
        candidateCreateDto.setLastName(lastName);
        LocalDate birthDate= LocalDate.parse("2000-08-10");
        candidateCreateDto.setBirthDate(birthDate);
        String phoneNumber="phoneNumber";
        candidateCreateDto.setPhoneNumber(phoneNumber);
        String  email ="email";
        candidateCreateDto.setEmail(email);
        String password="password";
        candidateCreateDto.setPassword(password);

        Candidate candidate =Candidate.builder()
                .firstName("firstName")
                .lastName("lastName")
                .birthDate(LocalDate.parse("2000-08-10"))
                .phoneNumber("123123")
                .build();

        CandidateDto candidateDto= CandidateDto.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .birthDate(candidate.getBirthDate())
                .phoneNumber(candidate.getPhoneNumber())
                .build();

        Mockito.when(candidateService.create(candidateCreateDto.toCandidate()))
                .thenReturn(new Candidate());
        CandidateDto reslt = underTest.create(candidateCreateDto);
        verify(candidateService).create(candidateCreateDto.toCandidate());

    }

    @Test
    void whenGetAllCandidate(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));
        List<Candidate> candidateList =new ArrayList<>();
        Candidate candidate1 =new Candidate();
        Candidate candidate2 =new Candidate();
        candidateList.add(candidate1);
        candidateList.add(candidate2);

        CandidateDto candidateDto= CandidateDto.builder()
                .id(123)
                .firstName("alper")
                .lastName("kınalı")
                .birthDate(LocalDate.ofEpochDay(2000-10-10))
                .phoneNumber("2323")
                .email("21231")
                .build();

        when(candidateService.getPagesOfCandidates(pageable)).thenReturn(new PageImpl<>(candidateList));

    }

    @Test
    void whenMakeApplication(){
        Long candidateId=123L;
        Long jobAdverbId=123L;
        when(candidateService.makeApplication(candidateId,jobAdverbId)).thenReturn(new Candidate());
        underTest.makeApplication(candidateId,jobAdverbId);
        verify(interviewService).makeAplicationInterview(candidateId,jobAdverbId);
        verify(candidateService).makeApplication(candidateId,jobAdverbId);

    }
    @Test
    void whenGetAllJobAdvertismentByCandidate(){
        List<JobAdvertisement> jobAdvertisementList =new ArrayList<>();
        JobAdvertisement jr = new JobAdvertisement();
        Long candidateId=1L;
        JobAdvertisement job1 =new JobAdvertisement();
        JobAdvertisement job2 =new JobAdvertisement();
        jobAdvertisementList.add(job1);
        jobAdvertisementList.add(job2);
        when(jobAdvertisementService.getAllByCandidateId(candidateId)).thenReturn(new ArrayList<>());
        underTest.getAllByCandidateId(candidateId);
        verify(jobAdvertisementService).getAllByCandidateId(candidateId);
        assertEquals(new ArrayList<>(),underTest.getAllByCandidateId(candidateId));
    }

}