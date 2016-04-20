package com.charilog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.api.param.ReqUploadCyclingRecord;
import com.charilog.domain.CyclingRecord;
import com.charilog.repository.CyclingRecordJpaRepository;
import com.charilog.repository.CyclingRecordRepository;

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

	public CyclingRecord create(ReqUploadCyclingRecord requestBody) {

		// ToDo : {userId, deviceId, dateTime}が同じレコードは登録できないようにする

		CyclingRecord record = new CyclingRecord(requestBody);
		return cyclingRecordJpaRepository.save(record);
	}

	public List<CyclingRecord> findByUserId(String userId) {
		return cyclingRecordRepository.findByUserId(userId);
	}

	public CyclingRecord find(String userId, String deviceId, Long dateTime) {
		List<CyclingRecord> records = cyclingRecordRepository.find(userId, deviceId, dateTime);

		if (records.isEmpty()) {
			return null;
		} else {
			return records.get(0);
		}
	}

	public void delete(Integer id) {
		cyclingRecordJpaRepository.delete(id);
	}
}
