package com.charilog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charilog.domain.KeyToRecord;

public interface KeyToRecordJpaRepository extends JpaRepository<KeyToRecord, String> {
}
