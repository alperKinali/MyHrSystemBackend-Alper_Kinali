package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.JobAdvertisementCreateDto;
import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.CandidateRepository;
import com.example.humanressapp.repository.CityRepository;
import com.example.humanressapp.repository.JobAdvertisementRepositroy;
import com.example.humanressapp.repository.StepRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JobAdvertisementServiceTest {

    @Mock
    private  JobAdvertisementRepositroy jobAdvertisementRepositroy;
    @Mock
    private  HRService hrService;
    @Mock
    private  CandidateRepository candidateRepository;
    @Mock
    private  CityRepository cityRepository;
    @Mock
    private  StepRepository stepRepository;

    @InjectMocks
    private JobAdvertisementService underTest;
    // bu test objesi.

    @Test
    public void sholudThrowExceptionThreIsNoHrBeforeCreatingJobadvertisment(){
        JobAdvertisementCreateDto dto = new  JobAdvertisementCreateDto();
        String title="Title";
        dto.setTitle(title);
        String description ="description";
        dto.setDescription(description);
        int cityId=2;
        dto.setCityId(cityId);
        long hrId=123;
        dto.setHrId(hrId);
        when(hrService.getHrById(hrId)).thenReturn(Optional.of(HR.builder().build()));
        assertThrows(NoSuchElementException.class, ()-> underTest.createJobAdvertisement(dto));
        verify(hrService).getHrById(hrId);
    }

    @Test
    void whenGetAllJobadvertismentByPage(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));
        List<JobAdvertisement> jrList =new ArrayList<>();
        JobAdvertisement jr1 =new JobAdvertisement();
        JobAdvertisement jr2 =new JobAdvertisement();
        jrList.add(jr1);
        jrList.add(jr2);
        when(jobAdvertisementRepositroy.findAll(pageable)).thenReturn(new PageImpl<>(jrList));
        underTest.getAllByPage(pageable);
        verify(jobAdvertisementRepositroy).findAll(pageable);
        assertEquals(2,underTest.getAllByPage(pageable).getContent().size());

    }
    @Test
    void whenJobadvertismentAppliedByTheCandidateAreCalled(){
        Long candidateId=1L;
        List<JobAdvertisement> jrList =new ArrayList<>();
        JobAdvertisement jr1 =new JobAdvertisement();
        JobAdvertisement jr2 =new JobAdvertisement();
        jrList.add(jr1);
        jrList.add(jr2);

        assertThrows(NotFountException.class ,()-> underTest.getAllByCandidateId(candidateId));
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(Candidate.builder().build()));
        Candidate mock = Mockito.mock(Candidate.class);
        when(mock.getJobAdvertisements()).thenReturn(jrList);
        verify(candidateRepository).findById(candidateId);
        assertEquals(mock.getJobAdvertisements(),jrList);
    }
    @Test
    void whenExistJobadvetismentIdForDelete(){
        JobAdvertisement jobAdvertisement= new JobAdvertisement();
        JobAdvertisement mockJr= Mockito.mock(JobAdvertisement.class);
        long jrId=123;
        //assertThrows(NotFountException.class, ()-> underTest.deleteById(1L));
        when(jobAdvertisementRepositroy.findById(jrId)).thenReturn(Optional.of(jobAdvertisement));
        when(jobAdvertisementRepositroy.save(jobAdvertisement)).thenReturn(new JobAdvertisement());
    }
    @Test
    void whenDontExistJobadvetismentIdForDelete(){
        assertThrows(NotFountException.class, ()-> underTest.deleteById(1L));
    }
    @Test
    void whenExistJobadvetismentIdForMakeActive(){
        JobAdvertisement jobAdvertisement= new JobAdvertisement();
        JobAdvertisement mockJr= Mockito.mock(JobAdvertisement.class);
        long jrId=123;
        //assertThrows(NotFountException.class, ()-> underTest.deleteById(1L));
        when(jobAdvertisementRepositroy.findById(jrId)).thenReturn(Optional.of(jobAdvertisement));
        when(jobAdvertisementRepositroy.save(jobAdvertisement)).thenReturn(new JobAdvertisement());
    }
    @Test
    void whenDontExistJobadvetismentIdMakeAktive(){
        assertThrows(NotFountException.class, ()-> underTest.makeActiveJobAdverb(1L));
    }
    @Test
    void whenUpdateJobadvertismentCalledValidParameter(){
        JobAdvertisement jobAdvertisement =new JobAdvertisement();
        Long jobAdverbId=1L;
        String text="text";
        String desc="desc";
        when(jobAdvertisementRepositroy.findById(jobAdverbId)).thenReturn(Optional.of(jobAdvertisement));
        when(jobAdvertisementRepositroy.save(jobAdvertisement)).thenReturn(jobAdvertisement);
        JobAdvertisement updatedJobadvertisment=underTest.updateJobAdverbTittleById(jobAdverbId,text,desc);
        assertEquals(jobAdvertisement,updatedJobadvertisment);
        verify(jobAdvertisementRepositroy).findById(1L);
        verify(jobAdvertisementRepositroy).save(jobAdvertisement);
    }
    @Test
    void sholudThrowNotFoundExceptionWhenThereIsNoJobadvertismentBeforeUpdateing(){
        JobAdvertisement jobAdvertisement =new JobAdvertisement();
        Long jobAdverbId=1L;
        String text="text";
        String desc="desc";
        assertThrows(NotFountException.class, ()-> underTest.updateJobAdverbTittleById(1L,text,desc));
    }
    @Test
    public void itshouldfindJobadvertismentId(){
        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        //Long hrId= hr.getId();
        Long jrId=1L;
        when(jobAdvertisementRepositroy.findById(jrId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        underTest.getById(jrId);
        verify(jobAdvertisementRepositroy).findById(jrId);
    }

    @Test
    public void itShouldGetAllActiveJobadvertisment(){
        List<JobAdvertisement> jobAdvertisementList =new ArrayList<>();
        JobAdvertisement jr = new JobAdvertisement();
        JobAdvertisement job1 =new JobAdvertisement();
        JobAdvertisement job2 =new JobAdvertisement();
        jobAdvertisementList.add(job1);
        jobAdvertisementList.add(job2);
        when(jobAdvertisementRepositroy.findAllActive()).thenReturn(new ArrayList<>());
        underTest.getAllActive();
        verify(jobAdvertisementRepositroy).findAllActive();
        assertEquals(new ArrayList<>(),underTest.getAllActive());
    }

    @Test
    public void whenAllJobadvertismentOpenedByHrAreDisplayed(){
        List<JobAdvertisement> jobAdvertisementList =new ArrayList<>();
        JobAdvertisement jr = new JobAdvertisement();
        Long hrId=1L;
        JobAdvertisement job1 =new JobAdvertisement();
        JobAdvertisement job2 =new JobAdvertisement();
        jobAdvertisementList.add(job1);
        jobAdvertisementList.add(job2);
        when(jobAdvertisementRepositroy.findByHrId(hrId)).thenReturn(new ArrayList<>());
        underTest.getByHRId(hrId);
        verify(jobAdvertisementRepositroy).findByHrId(hrId);
        assertEquals(new ArrayList<>(),underTest.getByHRId(hrId));

    }

}