package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.GPSElement;
import com.example.domain.GPSData;
import com.example.repository.GPSDataJpaRepository;

@Service
@Transactional
public class GPSDataService {

	@Autowired
	GPSDataJpaRepository gpsDataJpaRepository;

	public List<GPSData>findAll() {
		return gpsDataJpaRepository.findAll();
	}

	public GPSData create(GPSElement request, Integer recordId) {
		GPSData gpsData = new GPSData(request);
		gpsData.setRecordId(recordId);
		return gpsDataJpaRepository.save(gpsData);
	}
}
