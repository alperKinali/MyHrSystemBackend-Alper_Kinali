package com.example.humanressapp.service.abstracts;


import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.CandidateRepository;
import com.example.humanressapp.repository.HRRepository;
import com.example.humanressapp.repository.JobAdvertisementRepositroy;
import com.example.humanressapp.service.abstracts.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HRService   {


    private final HRRepository hrRepository;
    private final JobAdvertisementRepositroy jobAdvertisementRepositroy;
    private final CandidateRepository candidateRepository;

    public HRService(HRRepository hrRepository
            ,JobAdvertisementRepositroy jobAdvertisementRepositroy,
                     CandidateRepository candidateRepository) {
        this.hrRepository = hrRepository;
        this.jobAdvertisementRepositroy=jobAdvertisementRepositroy;
        this.candidateRepository=candidateRepository;
    }

    public HR create(HR hr) {
        return hrRepository.save(hr);
    }

    public Optional<HR> getHrById(long hrId) {
        return hrRepository.findById(hrId);
    }
    public Page<HR> getPagesOfHRs(Pageable page) {
        return hrRepository.findAll(page);
    }

    // yapılan başvuruyu görüntülemek için.
    public JobAdvertisement getById(Long id){
        return this.jobAdvertisementRepositroy.findById(id).get();
    }







}
