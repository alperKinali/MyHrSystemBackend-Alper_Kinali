package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.StepCreateDto;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.repository.StepRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StepServiceTest {
    // bağımlılıklar.
    @Mock
    private StepRepository stepRepository;
    @Mock
    private  JobAdvertisementService jobAdvertisementService;
    @Mock
    private  CandidateService candidateService;

    @InjectMocks
    private StepService underTest;
    // bu test objesi.

    @Test
    void sholudThrowNotFoundExceptionWhenThereIsNoJobadvetismentBeforeCreatingStep(){
        // önce bir Jobadvertismen çekilip çekilmediği kontrol edilmelidir.

        StepCreateDto dto =  new StepCreateDto();
        long jobAdvertisementId=123;
        int sOrder=1;
        String stepName="stepName";

        dto.setStepName(stepName);
        dto.setSOrder(sOrder);
        dto.setJobAdvertisementId(jobAdvertisementId);
        when(jobAdvertisementService.getById(jobAdvertisementId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        underTest.createStep(dto);
        verify(jobAdvertisementService).getById(jobAdvertisementId);

    }
    @Test
    void shouldGetByStepId(){

        Object object = new Object();
        Long onjectId=1L;
        when(stepRepository.findById(onjectId)).thenReturn(Optional.of(Step.builder().build()));
        underTest.getById(onjectId);
        verify(stepRepository).findById(onjectId);

    }

}