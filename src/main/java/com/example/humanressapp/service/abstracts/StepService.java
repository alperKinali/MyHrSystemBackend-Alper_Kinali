package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.StepCreateDto;
import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.model.Step;
import com.example.humanressapp.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StepService {
    private  final StepRepository stepRepository;
    private final JobAdvertisementService jobAdvertisementService;
    private final CandidateService candidateService;

    @Autowired
    public StepService(StepRepository stepRepository,
                       JobAdvertisementService jobAdvertisementService,
                       CandidateService candidateService) {
        this.stepRepository = stepRepository;
        this.jobAdvertisementService = jobAdvertisementService;
        this.candidateService = candidateService;
    }
    public void createStep(StepCreateDto dto){
        Optional<JobAdvertisement> jobAdvertisementOptional=this.jobAdvertisementService.getById(dto.getJobAdvertisementId());
        JobAdvertisement jobAdvertisement=jobAdvertisementOptional.orElseThrow(()->  new NotFountException("Job Advetisment id : "+dto.getJobAdvertisementId()+"is not found"));
        //JobAdvertisement jobAdvertisement = this.jobAdvertisementService.getById(dto.getJobAdvertisementId()).get();
        if (dto.getSOrder()>5){
            // en fazla 4 mülakat tanımlanabilmektedir.
            // aksi durumda hata fırlatılır.
            throw new IllegalArgumentException("No more than 5 interviews can be defined for a job  Advertisment");
        }
        this.stepRepository.save(
                Step.builder()
                        .stepName(dto.getStepName())
                        .sOrder(dto.getSOrder())
                        .jobAdvertisement(jobAdvertisement)
                        .build()
        );
    }
    public  Step getById(long id){
        return this.stepRepository.findById(id).orElse(null);
    }
}
