package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.CandidateRepository;
import com.example.humanressapp.repository.JobAdvertisementRepositroy;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobAdvertisementRepositroy jobAdvertisementRepositroy;
    @Mock
    private  JobAdvertisementService jobAdvertisementService;

    @InjectMocks
    private CandidateService underTest;
    // bu test objesi.

    @Test
    public void whenCreateHr(){
        Candidate candidate_obj=new Candidate();
        Mockito.when(candidateRepository.save(candidate_obj)).thenReturn(new Candidate());
        underTest.create(candidate_obj);
        verify(candidateRepository).save(new Candidate());
    }

    @Test
    public void itshouldfindbyCandidateId(){
        Candidate candidate = new Candidate();
        Long candidateId=1L;
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(Candidate.builder().build()));
        underTest.getCandidateById(candidateId);
        verify(candidateRepository).findById(candidateId);
    }

    @Test
    void  shouldGetAllCandidate(){
        //
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));
        List<Candidate> candidateList =new ArrayList<>();
        Candidate can1 =new Candidate();
        Candidate can2 =new Candidate();
        candidateList.add(can1);
        candidateList.add(can2);
        when(candidateRepository.findAll(pageable)).thenReturn(new PageImpl<>(candidateList));
        underTest.getPagesOfCandidates(pageable);
        assertEquals(2,underTest.getPagesOfCandidates(pageable).getContent().size());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenTherIsNoCandidateBeforeMakeApplication(){
        Candidate candidate = new Candidate();
        Long candidateId=123L;
        Long jobAdverbId =123L;
        assertThrows(NotFountException.class,() -> underTest.makeApplication(candidateId,jobAdverbId));
        when(jobAdvertisementRepositroy.findById(jobAdverbId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        when(candidateRepository.save(candidate)).thenReturn(new Candidate());
        verify(candidateRepository).findById(jobAdverbId);

    }

    @Test
    public void itshouldGetCandidatebyId(){
        Long candidateId=1L;
        underTest.getById(candidateId);
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(Candidate.builder().build()));
        verify(candidateRepository).findById(candidateId);
    }

    @Test
    public  void itShouldGetCandidatesForJobAdvertisment(){
        Long jobAdvertId=1L;
        List<Candidate> candidateList =new ArrayList<>();
        Candidate can1 =new Candidate();
        Candidate can2 =new Candidate();
        candidateList.add(can1);
        candidateList.add(can2);
        assertThrows(NotFountException.class ,()-> underTest.getCandidatesForJobAdvertisement(jobAdvertId));
        when(jobAdvertisementRepositroy.findById(jobAdvertId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        JobAdvertisement mock = Mockito.mock(JobAdvertisement.class);
        when(mock.getCandidates()).thenReturn(candidateList);
        verify(jobAdvertisementRepositroy).findById(jobAdvertId);
        assertEquals(mock.getCandidates(),candidateList);
    }


}