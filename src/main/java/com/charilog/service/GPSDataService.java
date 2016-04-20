package com.charilog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.api.param.GPSElement;
import com.charilog.domain.GPSData;
import com.charilog.repository.GPSDataJpaRepository;
import com.charilog.repository.GPSDataRepository;

@Service
@Transactional
public class GPSDataService {

	@Autowired
	private GPSDataJpaRepository gpsDataJpaRepository;

	@Autowired
	private GPSDataRepository gpsDataRepository;

	public List<GPSData>findAll() {
		return gpsDataJpaRepository.findAll();
	}

	public GPSData create(GPSElement request, Integer recordId) {
		GPSData gpsData = new GPSData(request);
		gpsData.setRecordId(recordId);
		return gpsDataJpaRepository.save(gpsData);
	}

	public void deleteByRecordId(Integer recordId) {
		gpsDataRepository.deleteByRecordId(recordId);
	}
}
