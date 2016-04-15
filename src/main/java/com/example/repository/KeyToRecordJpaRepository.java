package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.KeyToRecord;

public interface KeyToRecordJpaRepository extends JpaRepository<KeyToRecord, String> {
}
