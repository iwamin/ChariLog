package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.KeyToRecord;
import com.example.repository.KeyToRecordJpaRepository;

@Service
@Transactional
public class KeyToRecordService {

	@Autowired
	KeyToRecordJpaRepository keyToRecordJpaRepository;

	public KeyToRecord find(String key) {
		return keyToRecordJpaRepository.findOne(key);
	}

	public KeyToRecord create(KeyToRecord entity) {
		return keyToRecordJpaRepository.save(entity);
	}

}
