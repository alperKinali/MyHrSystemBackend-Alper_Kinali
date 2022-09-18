package com.example.humanressapp.Controller;

import com.example.humanressapp.Controller.dto.CandidateDto;
import com.example.humanressapp.Controller.dto.HRDto;
import com.example.humanressapp.Controller.dto.JobAdvertisementCreateDto;
import com.example.humanressapp.Controller.dto.JobAdvertisementDto;
import com.example.humanressapp.model.HR;
import com.example.humanressapp.model.JobAdvertisement;
import com.example.humanressapp.service.abstracts.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class JobAdvertisementController {
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }


    @PostMapping("jobAdvertisementCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody JobAdvertisementCreateDto dto) {
        jobAdvertisementService.createJobAdvertisement(dto);
    }
    // burata page şeklinde başvurualr görüntülensin
    /*
    @GetMapping("jobAdvertisements")
    public Page<JobAdvertisementDto> getAll(Pageable pageable){
        return jobAdvertisementService.getAllByPage(pageable)
                .map(jobAdvertisementDto -> JobAdvertisementDto.builder()
                        .id(jobAdvertisementDto.getId())
                        .title(jobAdvertisementDto.getTitle())
                        .description(jobAdvertisementDto.getDescription())
                        .city(jobAdvertisementDto.getCity())
                        .build());
    }

     */

    // burada delete kısmı olsun (soft delete)
    @PutMapping("/deleteJobAdvertisment/{id}")
    public ResponseEntity<Void> deleteJobAdverb(@PathVariable Long id){
        this.jobAdvertisementService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // burada aktif olamyan iş ilanı tekrardan active olur
    @PutMapping("/makeActiceJobAdverb/{id}")
    public ResponseEntity<Void> makeActiveJobAdverb(@PathVariable Long id){
        jobAdvertisementService.makeActiveJobAdverb(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // iş başvurusu  güncelleme.
    @PutMapping("/{jobAdverbId}")
    public JobAdvertisement updateJobAdverbTitle(@RequestParam Long jobAdverbId,@RequestParam String title,@RequestParam String desc ){
        return jobAdvertisementService.updateJobAdverbTittleById(jobAdverbId,title,desc);
    }

    // tüm silinmemiş yani aktif olan işleri tekrar getiriyor.
    @GetMapping("/getAll")
    public List<JobAdvertisement> getAll (){
        return  this.jobAdvertisementService.getAllActive();
    }



}
