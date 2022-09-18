package com.example.humanressapp.repository;

import com.example.humanressapp.model.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertisementRepositroy extends JpaRepository<JobAdvertisement, Long> {
    // burada iş ilanlarını listeletirken
    // sadece isActive olanların, true olanların listelenmesi sağlandı
    @Query("SELECT j from JobAdvertisement j where j.isActive=true ")
    List<JobAdvertisement> findAllActive();

    List<JobAdvertisement> findByHrId(Long hrId);


}
