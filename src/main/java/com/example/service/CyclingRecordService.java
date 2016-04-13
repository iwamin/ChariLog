package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.RequestBodyCyclingRecord;
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

	public CyclingRecord create(RequestBodyCyclingRecord requestBody) {

		// ToDo : {userId, deviceId, dateTime}が同じレコードは登録できないようにする

		CyclingRecord record = new CyclingRecord(requestBody);
		return cyclingRecordJpaRepository.save(record);
	}
}
