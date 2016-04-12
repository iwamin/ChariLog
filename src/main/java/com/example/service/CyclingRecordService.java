package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CyclingRecord;
import com.example.repository.CyclingRecordJpaRepository;
import com.example.repository.CyclingRecordRepository;

@Service
@Transactional
public class CyclingRecordService {
	@Autowired
	CyclingRecordJpaRepository cyclingRecordJpaRepository;

	@Autowired
	CyclingRecordRepository cyclingRecordRepository;

	public List<CyclingRecord> findAll() {
		return cyclingRecordJpaRepository.findAll();
	}

	public CyclingRecord create(CyclingRecord record) {
		return cyclingRecordJpaRepository.save(record);
	}
}
