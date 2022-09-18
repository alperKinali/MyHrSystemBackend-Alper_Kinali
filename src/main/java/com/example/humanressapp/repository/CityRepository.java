package com.example.humanressapp.repository;

import com.example.humanressapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
