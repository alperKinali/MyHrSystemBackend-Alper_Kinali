package com.example.humanressapp.service.abstracts;

import com.example.humanressapp.Controller.dto.JobAdvertisementCreateDto;
import com.example.humanressapp.NotFountException;
import com.example.humanressapp.model.Candidate;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobAdvertisementService {
    private final JobAdvertisementRepositroy jobAdvertisementRepositroy;
    private final HRService hrService;
    private final  CandidateRepository candidateRepository;
    private final CityRepository cityRepository;
    private final StepRepository stepRepository;



    @Autowired
    public JobAdvertisementService(JobAdvertisementRepositroy jobAdvertisementRepositroy,
                                   HRService hrService,CandidateRepository candidateRepository,
                                   CityRepository cityRepository,
                                   StepRepository stepRepository) {
        this.jobAdvertisementRepositroy = jobAdvertisementRepositroy;
        this.hrService=hrService;
        this.candidateRepository=candidateRepository;
        this.cityRepository = cityRepository;
        this.stepRepository = stepRepository;
    }

    public void createJobAdvertisement(JobAdvertisementCreateDto dto){
        Optional<HR> hrOptional = hrService.getHrById(dto.getHrId());
        HR hr= hrOptional.orElseThrow(() -> new NotFountException("Hr with id: " + dto.getHrId() + " is not found"));
        jobAdvertisementRepositroy.save(
                JobAdvertisement.builder()
                        .hr(hr)
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .city(this.cityRepository.findById(dto.getCityId()).get())
                        .isActive(true)
                        .build());

    }
    public Page<JobAdvertisement> getAllByPage(Pageable pageable){
        return jobAdvertisementRepositroy.findAll(pageable);
    }
    // burada adayın başvurduğu ilanlar listelenecektir.
    public List<JobAdvertisement> getAllByCandidateId(Long id){
        Optional<Candidate> candidateOptional= this.candidateRepository.findById(id);
        Candidate candidate = candidateOptional.orElseThrow(() -> new NotFountException("There is no candidate with this id "));
        return candidate.getJobAdvertisements();
    }

    // Soft Delete işlemi
    public void deleteById(Long id) {
        if (jobAdvertisementRepositroy.existsById(id)){
            //jobAdvertisementRepositroy.deleteById(id);
            JobAdvertisement jobAdvertisement= jobAdvertisementRepositroy.findById(id).get();
            jobAdvertisement.setActive(false);
            jobAdvertisementRepositroy.save(jobAdvertisement);
        }
        else {
            throw  new NotFountException("Job advertisment id must be exist");
        }
    }
    // tekrardan silinen işi geri almak için..
    public void makeActiveJobAdverb(Long id){
        if (jobAdvertisementRepositroy.existsById(id)){
            JobAdvertisement jobAdvertisement= jobAdvertisementRepositroy.findById(id).get();
            jobAdvertisement.setActive(true);
            jobAdvertisementRepositroy.save(jobAdvertisement);
        }
        else {
            throw  new NotFountException("Job advertisment id must exist");
        }
    }

    public JobAdvertisement updateJobAdverbTittleById(Long jobAdverbId,String text,String desc) {
        // id'si verilen iş başvurusu var mı yok mu ?
        Optional<JobAdvertisement> jobAdvertisement=jobAdvertisementRepositroy.findById(jobAdverbId);
        if (jobAdvertisement.isPresent()){
            JobAdvertisement toUpdate =jobAdvertisement.get();
            toUpdate.setTitle(text);
            toUpdate.setDescription(desc);
            return jobAdvertisementRepositroy.save(toUpdate);

        }else {
            throw  new NotFountException("There Is No Jobadvertisment With This Id");

        }
    }
    public Optional<JobAdvertisement> getById(Long id) {
        return jobAdvertisementRepositroy.findById(id);
    }

    public List<JobAdvertisement> getAllActive(){
        return this.jobAdvertisementRepositroy.findAllActive();
        // burası başvurulacak aktif yani açık iş ilanlarını görüntülemek için
    }

    // hr'in açtığı iş ilanlarını görüntülemek için
    public List<JobAdvertisement> getByHRId(Long hrId){
        return this.jobAdvertisementRepositroy.findByHrId(hrId);
    }


}
