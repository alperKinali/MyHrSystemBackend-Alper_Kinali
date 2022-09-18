package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.JobAdvertisementCreateDto;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.JobAdvertisementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class JobAdvertisementControllerTest {

    @Mock
    private JobAdvertisementService jobAdvertisementService;

    @InjectMocks
    private JobAdvertisementController underTest;

    @Test
    void create_shouldCreateSuccessfully(){
        JobAdvertisementCreateDto dto=  new JobAdvertisementCreateDto();
        dto.setTitle("title");
        dto.setDescription("desc");
        dto.setCityId(1);
        dto.setHrId(123L);
        underTest.createReservation(dto);
        Mockito.verify(jobAdvertisementService,Mockito.times(1)).createJobAdvertisement(dto);
    }
    @Test
    void whenDeleteJobadvertisment(){
        Long jobAdvertId=1L;
        ResponseEntity<Void> response=underTest.deleteJobAdverb(jobAdvertId);
        //underTest.deleteJobAdverb(jobAdvertId);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void whenMakActiveAgainJobadvertisment(){
        Long jobAdvertId=1L;
        ResponseEntity<Void> response=underTest.makeActiveJobAdverb(jobAdvertId);
        //underTest.deleteJobAdverb(jobAdvertId);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void  whenUpdateJobadvertismentParameter(){
        JobAdvertisement jobAdvertisement= new JobAdvertisement();
        Long jobAdverbId=1L;
        String title="title";
        String desc="desc";
        underTest.updateJobAdverbTitle(jobAdverbId,title,desc);
        Mockito.when(jobAdvertisementService.updateJobAdverbTittleById(jobAdverbId,title,desc)).thenReturn(new JobAdvertisement());
        Mockito.verify(jobAdvertisementService,Mockito.times(1)).updateJobAdverbTittleById(jobAdverbId,title,desc);

    }
    @Test
    void whenGetAllActiveJobadvertismentForCandidateApp(){
        List<JobAdvertisement> jobAdvertisementList= new ArrayList<>();
        JobAdvertisement jr1= new JobAdvertisement();
        JobAdvertisement jr2= new JobAdvertisement();
        jobAdvertisementList.add(jr1);
        jobAdvertisementList.add(jr2);
        Mockito.when(jobAdvertisementService.getAllActive()).thenReturn(new ArrayList<>());
        underTest.getAll();
        Mockito.verify(jobAdvertisementService).getAllActive();
        assertEquals(new ArrayList<>(),underTest.getAll());

    }


}