package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.CandidateRepository;
import com.example.humanressapp.repository.HRRepository;
import com.example.humanressapp.repository.JobAdvertisementRepositroy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HRServiceTest {

    @Mock
    private HRRepository hrRepository;
    @Mock
    private JobAdvertisementRepositroy jobAdvertisementRepositroy;
    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private HRService underTest;
    // bu test objesi.

    @Test
    public void whenCreateHr(){
        HR hr_obj=new HR();
        Mockito.when(hrRepository.save(hr_obj)).thenReturn(new HR());
        underTest.create(hr_obj);
        verify(hrRepository).save(hr_obj);
    }

    @Test
    public void itshouldfindhrbyId(){
        HR hr = new HR();
        //Long hrId= hr.getId();
        Long hrId=1L;
        when(hrRepository.findById(hrId)).thenReturn(Optional.of(HR.builder().build()));
        underTest.getHrById(hrId);
        verify(hrRepository).findById(hrId);
    }

    @Test
    void  shouldGetAllHr(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));
        List<HR> hrList =new ArrayList<>();
        HR hr1 =new HR();
        HR hr2 =new HR();
        hrList.add(hr1);
        hrList.add(hr2);
        when(hrRepository.findAll(pageable)).thenReturn(new PageImpl<>(hrList));
        Page<HR> result=underTest.getPagesOfHRs(pageable);
        //verify(hrRepository).findAll();
        assertEquals(2,underTest.getPagesOfHRs(pageable).getContent().size());
    }

    @Test
    void itshouldGetByJobAdvertismentId(){
        JobAdvertisement jr =new JobAdvertisement();
        Long jrId=1L;
        when(jobAdvertisementRepositroy.findById(jrId)).thenReturn(Optional.of(JobAdvertisement.builder().build()));
        underTest.getById(jrId);
        verify(jobAdvertisementRepositroy).findById(jrId);

    }


}