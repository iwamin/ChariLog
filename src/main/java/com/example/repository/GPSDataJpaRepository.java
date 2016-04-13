package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.GPSData;

public interface GPSDataJpaRepository extends JpaRepository<GPSData, Integer> {

}
