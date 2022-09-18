package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.InterviewCreateDto;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.Interview;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.repository.InterviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InterviewServiceTest {
    //bağımlılıklar.
    @Mock
    private InterviewRepository interviewRepository;

    @Mock
    private  CandidateService candidateService;

    @Mock
    private  JobAdvertisementService jobAdvertisementService;

    @Mock
    private   StepService stepService;

    @InjectMocks
    private InterviewService underTest;
    // bu test objesi

    @Test
    public void whenCreateInterviewWithValidDto(){
        InterviewCreateDto dto=new InterviewCreateDto();
        boolean result=true;
        dto.setResult(result);
        String notes="notes";
        dto.setNotes(notes);
        Long candidateId=1L;
        dto.setCandidateId(candidateId);
        long stepId=123L;
        dto.setStepId(stepId);
        // Interview nesnesi oluşturalım.
        Interview interview= new Interview();
        Mockito.when(interviewRepository.save(interview)).thenReturn(interview);
        underTest.createInterview(dto);
    }

    @Test
    public  void  shouldExWhenThereIsNoJobAdvertismentAndCandidateIdBeforeMakeApp(){
        // hata aldıktanki kısım.
        long candidateId=123;
        long jobAdvetId=123;
        JobAdvertisement jobAdvertisement= new JobAdvertisement();
        Mockito.when(candidateService.getById(candidateId)).thenReturn(new Candidate());
        Mockito.when(jobAdvertisementService.getById(jobAdvetId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        assertThrows(NullPointerException.class,()-> underTest.makeAplicationInterview(candidateId,jobAdvetId));
        Mockito.verify(candidateService).getById(candidateId);
        Mockito.verify(jobAdvertisementService).getById(jobAdvetId);
        //Optional<JobAdvertisement> jobAdvertisementOptional =this.jobAdvertisementService.getById(jobAdvetId);
    }
    @Test
    public  void  whenMakeApp(){
        // hata alınmadı senaryosu testi fakat stepleri çekemedim.
        long candidateId=123;
        long jobAdvetId=123;
        JobAdvertisement jobAdvertisement= new JobAdvertisement();
        Mockito.when(candidateService.getById(candidateId)).thenReturn(new Candidate());
        Mockito.when(jobAdvertisementService.getById(jobAdvetId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        assertThrows(NullPointerException.class,()-> underTest.makeAplicationInterview(candidateId,jobAdvetId));
        Mockito.verify(candidateService).getById(candidateId);
        Mockito.verify(jobAdvertisementService).getById(jobAdvetId);
        //Mockito.when(jobAdvertisement.getSteps()).thenReturn(new ArrayList<>());

        Interview interview = new Interview();
        List<Step> steps =new ArrayList<>();
        Step step1 = new Step();
        Step step2 = new Step();
        steps.add(step1);
        steps.add(step2);
        when(interviewRepository.save(interview)).thenReturn(interview);
    }

    @Test
    public void whenSuccesStep(){
        long interviewId=123;
        String notes="notes";
        Interview interview = new Interview();
        Mockito.when(interviewRepository.findById(interviewId)).thenReturn(Optional.of(Interview.builder().build()));
        when(interviewRepository.save(interview)).thenReturn(new Interview());
        underTest.successStep(interviewId,notes);
        verify(interviewRepository).findById(interviewId);
    }

    @Test
    public  void whenFailStep(){
        long interviewId=123;
        String notes="notes";
        Interview interview = new Interview();
        when(interviewRepository.findById(interviewId)).thenReturn(Optional.of(Interview.builder().build()));

        when(interviewRepository.save(interview)).thenReturn(new Interview());

        underTest.faillStep(interviewId,notes);
        verify(interviewRepository).findById(interviewId);

    }


}