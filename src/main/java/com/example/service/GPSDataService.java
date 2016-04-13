package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.RequestBodyGPSData;
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

	public GPSData create(RequestBodyGPSData request) {
		GPSData gpsData = new GPSData(request);
		gpsData.setRecordId(123);
		return gpsDataJpaRepository.save(gpsData);
	}
}
