package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.InterviewCreateDto;
import com.example.humanressapp.model.Interview;
import com.example.humanressapp.service.abstracts.InterviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class InterviewControllerTest {
    @Mock
    private InterviewService interviewService;

    @InjectMocks
    private InterviewController underTest;


    @Test
    void create_shouldCreateSuccessfully(){
        // given
        Interview interview = new Interview();
        InterviewCreateDto dto= new InterviewCreateDto();
        dto.setResult(true);
        dto.setNotes("Notes");
        dto.setCandidateId(123L);
        dto.setStepId(123);
        Mockito.when(interviewService.createInterview(dto)).thenReturn(new Interview());
        underTest.createInterview(dto);
        Mockito.verify(interviewService,Mockito.times(1)).createInterview(dto);

    }
    @Test
    void whenMakeSuccessStep(){
        Interview interview= new Interview();
        long interviewId=123;
        String notes="notes";
        underTest.successStep(interviewId,notes);
        //Mockito.when(interviewService.successStep(interviewId,notes)).thenReturn(void)
        Mockito.verify(interviewService,Mockito.times(1)).successStep(interviewId,notes);
    }

    @Test
    void whenMakeFaildStep(){
        long interviewId=123;
        String notes="notes";
        underTest.faillStep(interviewId,notes);
        //Mockito.when(interviewService.successStep(interviewId,notes)).thenReturn(void)
        Mockito.verify(interviewService,Mockito.times(1)).faillStep(interviewId,notes);
    }

}