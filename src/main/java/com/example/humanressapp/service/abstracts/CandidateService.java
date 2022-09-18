package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.JobAdvertisementCreateDto;
import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.CandidateRepository;
import com.example.humanressapp.repository.JobAdvertisementRepositroy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService   {
    private final CandidateRepository candidateRepository;
    private final JobAdvertisementRepositroy jobAdvertisementRepositroy;
    private final JobAdvertisementService jobAdvertisementService;


    public CandidateService(CandidateRepository candidateRepository,
                            JobAdvertisementRepositroy jobAdvertisementRepositroy,
                            JobAdvertisementService jobAdvertisementService) {
        this.candidateRepository = candidateRepository;
        this.jobAdvertisementRepositroy=jobAdvertisementRepositroy;
        this.jobAdvertisementService=jobAdvertisementService;
    }


    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Optional<Candidate> getCandidateById(long candidateId) {
        return candidateRepository.findById(candidateId);
    }

    public Page<Candidate> getPagesOfCandidates(Pageable page) {
        return candidateRepository.findAll(page);
    }

    public  Candidate makeApplication(Long candidateId, Long jobAdverbId){
        // aday iş başvurusu yapması için
        Optional<Candidate> candidateOp= getCandidateById(candidateId);
        Candidate candidate=candidateOp.orElseThrow(()-> new NotFountException("There is no candidate Whit this id"));
        JobAdvertisement jobAdvertisement =this.jobAdvertisementRepositroy.findById(jobAdverbId).get();
        candidate.getJobAdvertisements().add(jobAdvertisement);
        // başvurulan iş ilanı adayın ilan listesine eklendi.
        return candidateRepository.save(candidate);
    }
    public Candidate getById(Long id){
        return this.candidateRepository.findById(id).orElse(null);
    }

    public List<Candidate> getCandidatesForJobAdvertisement(Long jobAdvertId){
        Optional<JobAdvertisement>jobAdvertisementOp=this.jobAdvertisementRepositroy.findById(jobAdvertId);
        JobAdvertisement jobAdvertisement=jobAdvertisementOp.orElseThrow(()-> new NotFountException("There is no JobAdverb with this id"));
        //JobAdvertisement jobAdvertisement = this.jobAdvertisementRepositroy.findById(jobAdvertId).get();
        return jobAdvertisement.getCandidates();
    }


}
