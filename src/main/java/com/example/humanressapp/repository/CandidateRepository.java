package com.example.humanressapp.repository;

import com.example.humanressapp.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

}
