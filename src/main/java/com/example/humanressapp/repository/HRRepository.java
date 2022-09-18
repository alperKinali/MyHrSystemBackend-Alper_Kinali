package com.example.humanressapp.repository;

import com.example.humanressapp.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HR, Long> {
}
