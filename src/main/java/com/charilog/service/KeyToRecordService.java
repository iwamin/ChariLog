package com.charilog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.domain.KeyToRecord;
import com.charilog.repository.KeyToRecordJpaRepository;
import com.charilog.repository.KeyToRecordRepository;

@Service
@Transactional
public class KeyToRecordService {

	@Autowired
	KeyToRecordJpaRepository keyToRecordJpaRepository;

	@Autowired
	KeyToRecordRepository keyToRecordRepository;

	public KeyToRecord find(String key) {
		return keyToRecordJpaRepository.findOne(key);
	}

	public KeyToRecord register(KeyToRecord entity) {
		// そのユーザーが使用していた古いkeyを削除する
		keyToRecordRepository.deleteByUserId(entity.getUserId());
		// 新しいキーを登録する
		return keyToRecordJpaRepository.save(entity);
	}
}
