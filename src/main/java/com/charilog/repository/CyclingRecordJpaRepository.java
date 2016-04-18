package com.charilog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charilog.domain.CyclingRecord;

public interface CyclingRecordJpaRepository extends JpaRepository<CyclingRecord, Integer> {
}
