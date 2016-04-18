package com.charilog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charilog.domain.GPSData;

public interface GPSDataJpaRepository extends JpaRepository<GPSData, Integer> {

}
