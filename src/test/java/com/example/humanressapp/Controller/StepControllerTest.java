package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.StepCreateDto;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.service.abstracts.StepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class StepControllerTest {
    @Mock
    private StepService stepService;

    @InjectMocks
    StepController underTest;

    @Test
    void createStep(){
        // given
        StepCreateDto dto= new StepCreateDto();
        dto.setStepName("alper");
        dto.setSOrder(1);
        dto.setJobAdvertisementId(123);
        underTest.createStep(dto);
        verify(stepService,times(1)).createStep(dto);
        ResponseEntity<Void> response=underTest.createStep(dto);
        Void actual= response.getBody();
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }
    @Test
    void getById_shouldReturnCustomerDto(){
        StepCreateDto dto= new StepCreateDto();
        long id=123;
        Step response = underTest.getById(id);

        when(stepService.getById(id)).thenReturn(new Step());
        verify(stepService).getById(id);

    }

}