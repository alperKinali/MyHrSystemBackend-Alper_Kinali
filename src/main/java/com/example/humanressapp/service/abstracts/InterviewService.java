package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.InterviewCreateDto;
import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.Interview;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.repository.InterviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final CandidateService candidateService;
    private final JobAdvertisementService jobAdvertisementService;
    private final  StepService stepService;

    public InterviewService(InterviewRepository interviewRepository
            , CandidateService candidateService
            , JobAdvertisementService jobAdvertisementService
            ,StepService stepService) {
        this.interviewRepository = interviewRepository;
        this.candidateService = candidateService;
        this.jobAdvertisementService = jobAdvertisementService;
        this.stepService= stepService;
    }

    public Interview createInterview(InterviewCreateDto dto){
        return this.interviewRepository.save(Interview.builder()
                .result(dto.isResult())
                .notes(dto.getNotes())
                .candidate(this.candidateService.getById(dto.getCandidateId()))
                .step(this.stepService.getById(dto.getStepId()))
                .build());
    }
    public void makeAplicationInterview(long candidateId,long jobAdvetId){
        Candidate candidate =this.candidateService.getById(candidateId);
        Optional<JobAdvertisement> jobAdvertisementOptional =this.jobAdvertisementService.getById(jobAdvetId);
        JobAdvertisement jobAdvertisement=jobAdvertisementOptional.orElseThrow(()->new NotFountException("It must be Jobadvertisment id"));
        for (Step step : jobAdvertisement.getSteps()){
            Interview interview = new Interview();
            // her adım için mulakak acıldı
            interview.setCandidate(candidate);
            interview.setStep(step);
            this.interviewRepository.save(interview);
        }
    }
    // mulakat geçemesi.
    public void  successStep(long interviewId, String notes){
        Interview interview =this.interviewRepository.findById(interviewId).get();
        interview.setResult(true);
        interview.setNotes(notes);
        this.interviewRepository.save(interview);
        // adayın mülakatı başarılı olarak güncellendi
    }
    public void  faillStep(long interviewId, String notes){
        Interview interview =this.interviewRepository.findById(interviewId).get();
        interview.setResult(false);
        interview.setNotes(notes);
        this.interviewRepository.save(interview);
        // adayın mülakatı başarısız  olarak güncellendi
    }
}
