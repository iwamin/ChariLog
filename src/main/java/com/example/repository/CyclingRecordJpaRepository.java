package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.CyclingRecord;

public interface CyclingRecordJpaRepository extends JpaRepository<CyclingRecord, Integer> {
}
