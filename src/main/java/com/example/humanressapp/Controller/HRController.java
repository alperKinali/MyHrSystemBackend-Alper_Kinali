package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.CandidateCreateDto;
import com.example.humanressapp.Controller.dto.CandidateDto;
import com.example.humanressapp.Controller.dto.HRCreateDto;
import com.example.humanressapp.Controller.dto.HRDto;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.CandidateService;
import com.example.humanressapp.service.abstracts.HRService;
import com.example.humanressapp.service.abstracts.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class HRController {
    private final HRService hrService;
    private final JobAdvertisementService jobAdvertisementService;
    private final CandidateService candidateService;

    @Autowired
    public HRController(HRService hrService,JobAdvertisementService jobAdvertisementService, CandidateService candidateService) {
        this.hrService = hrService;
        this.jobAdvertisementService = jobAdvertisementService;
        this.candidateService = candidateService;
    }

    @PostMapping("HR")
    public HRDto create(@RequestBody HRCreateDto dto ){
        HR hr = hrService.create(dto.toHR());
        return HRDto.builder()
                .id(hr.getId())
                .companyName(hr.getCompanyName())
                .webSite(hr.getWebSite())
                .adress(hr.getAdress())
                .build();

    }
    @GetMapping("hrs")
    public Page<HRDto> listHRs(Pageable page) {
        return hrService.getPagesOfHRs(page)
                .map(hr -> HRDto.builder()
                        .id(hr.getId())
                        .companyName(hr.getCompanyName())
                        .webSite(hr.getWebSite())
                        .adress(hr.getAdress())
                        .email(hr.getEmail())
                        .build());
    }


    @GetMapping("/getJobAdvertisementsByHrId/{hrId}")
    public List<JobAdvertisement> getJobAdvertisementByHrId(@PathVariable Long hrId){
        return this.jobAdvertisementService.getByHRId(hrId);
    }

    @GetMapping("/getCandidatesForJobAdvertisement/{jobAdvertId}")
    public List<Candidate> getCandidatesForJobAdvertisement(@PathVariable Long jobAdvertId){
        return this.candidateService.getCandidatesForJobAdvertisement(jobAdvertId);
        // işe başvuran adayların listelenmesi için
    }
}
