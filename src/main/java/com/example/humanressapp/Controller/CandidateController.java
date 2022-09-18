package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.CandidateCreateDto;
import com.example.humanressapp.Controller.dto.CandidateDto;
import com.example.humanressapp.Controller.dto.JobAdvertisementDto;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.CandidateService;
import com.example.humanressapp.service.abstracts.InterviewService;
import com.example.humanressapp.service.abstracts.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CandidateController {
    private final CandidateService candidateService;
    private final JobAdvertisementService jobAdvertisementService;
    private final InterviewService interviewService;

    @Autowired
    public CandidateController(CandidateService candidateService
            ,JobAdvertisementService jobAdvertisementService,
                               InterviewService interviewService) {
        this.candidateService = candidateService;
        this.jobAdvertisementService=jobAdvertisementService;
        this.interviewService=interviewService;

    }

    @PostMapping("candidate")
    public CandidateDto create(@RequestBody CandidateCreateDto dto){
        Candidate candidate = candidateService.create(dto.toCandidate());
        return CandidateDto.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .birthDate(candidate.getBirthDate())
                .phoneNumber(candidate.getPhoneNumber())
                .build();

    }

    @GetMapping("candidates")
    public Page<CandidateDto> listcandidates(Pageable page) {
        return candidateService.getPagesOfCandidates(page)
                .map(candidate -> CandidateDto.builder()
                        .id(candidate.getId())
                        .firstName(candidate.getFirstName())
                        .lastName(candidate.getLastName())
                        .birthDate(candidate.getBirthDate())
                        .phoneNumber(candidate.getPhoneNumber())
                        .email(candidate.getEmail())
                        .build());
    }


    @PutMapping("/{candidateId}/jobAdvert/{jobadvertId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate makeApplication(@PathVariable Long candidateId, @PathVariable Long jobadvertId){
        this.interviewService.makeAplicationInterview(candidateId,jobadvertId);
        // her başvuru yapıldığında kişiye mülakat tanımlanıyor.
        return this.candidateService.makeApplication(candidateId,jobadvertId);
    }

    @GetMapping("/getAllByCandidateId/{candidateId}")
    public List<JobAdvertisement> getAllByCandidateId(@PathVariable Long candidateId){
        return this.jobAdvertisementService.getAllByCandidateId(candidateId);
        // adayın basvurduğu tüm iş ilanları listeletmek için
    }

}

