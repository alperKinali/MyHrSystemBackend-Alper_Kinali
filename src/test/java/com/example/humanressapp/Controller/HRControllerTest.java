package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.HRCreateDto;
import com.example.humanressapp.Controller.dto.HRDto;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.CandidateService;
import com.example.humanressapp.service.abstracts.HRService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class HRControllerTest {

    @Mock
    private HRService hrService;
    @Mock
    private JobAdvertisementService jobAdvertisementService;
    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private HRController underTest;

    @Test
    void whenCreateHr(){
        HRCreateDto dto= new HRCreateDto();
        String companyName="companyName";
        dto.setCompanyName(companyName);
        String webSite="website";
        dto.setWebSite(webSite);
        String adress="Address";
        dto.setAdress(adress);
        String email="email";
        dto.setEmail(email);
        String password="123";
        dto.setPassword(password);
        HR hr=HR.builder()
                .companyName("companyName")
                .adress("adress")
                .webSite("website")
                .build();
        HRDto hrDto=HRDto.builder()
                .id(hr.getId())
                .companyName(hr.getCompanyName())
                .webSite(hr.getWebSite())
                .adress(hr.getAdress())
                .build();

        Mockito.when(hrService.create(dto.toHR())).thenReturn(new HR());
        HRDto result = underTest.create(dto);
        Mockito.verify(hrService).create(dto.toHR());
    }

    @Test
    void  whenGetAllHr(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));
        List<HR> HRlİST =new ArrayList<>();
        HR hr1 =new HR();
        HR hr2 =new HR();
        HRlİST.add(hr1);
        HRlİST.add(hr2);

        HRDto hrDto= HRDto.builder()
                .id(123)
                .companyName("companyName")
                .webSite("webSite")
                .adress("address")
                .email("21231")
                .build();
        when(hrService.getPagesOfHRs(pageable)).thenReturn(new PageImpl<>(HRlİST));

    }

    @Test
    void whenGetJobAdvertisementByHrId(){
        List<JobAdvertisement> jobAdvertisementList =new ArrayList<>();
        JobAdvertisement jr = new JobAdvertisement();
        Long hrId=1L;
        JobAdvertisement job1 =new JobAdvertisement();
        JobAdvertisement job2 =new JobAdvertisement();
        jobAdvertisementList.add(job1);
        jobAdvertisementList.add(job2);
        when(jobAdvertisementService.getByHRId(hrId)).thenReturn(new ArrayList<>());
        underTest.getJobAdvertisementByHrId(hrId);
        verify(jobAdvertisementService).getByHRId(hrId);
        assertEquals(new ArrayList<>(),underTest.getJobAdvertisementByHrId(hrId));
    }

    @Test
    void whenGetCandidatesForJobadvertisment(){
        List<Candidate> candidateArrayList =new ArrayList<>();
        Candidate candidate = new Candidate();
        Long jobAdvertismentId=1L;
        Candidate candidate1=new Candidate();
        Candidate candidate2= new Candidate();
        candidateArrayList.add(candidate1);
        candidateArrayList.add(candidate2);
        when(candidateService.getCandidatesForJobAdvertisement(jobAdvertismentId)).thenReturn(new ArrayList<>());
        underTest.getCandidatesForJobAdvertisement(jobAdvertismentId);
        verify(candidateService).getCandidatesForJobAdvertisement(jobAdvertismentId);
        assertEquals(new ArrayList<>(),underTest.getCandidatesForJobAdvertisement(jobAdvertismentId));
    }


}