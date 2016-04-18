package com.charilog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.domain.CyclingRecord;

import static com.charilog.constant.SQLConstants.*;

@Repository
@Transactional
public class CyclingRecordRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	private static final RowMapper<CyclingRecord> CYCLING_RECORD_ROW_MAPPER = (rs, i) -> {
		String userId = rs.getString(COLUMN_CYCRECORD_USER_ID);
		String deviceId = rs.getString(COLUMN_CYCRECORD_DEVICE_ID);
		Long dateTime = rs.getLong(COLUMN_CYCRECORD_DATE_TIME);
		String date = rs.getString(COLUMN_CYCRECORD_DATE);
		String startTime = rs.getString(COLUMN_CYCRECORD_START_TIME);
		String endTime = rs.getString(COLUMN_CYCRECORD_END_TIME);
		Long totalTime = rs.getLong(COLUMN_CYCRECORD_TOTAL_TIME);
		Integer distance = rs.getInt(COLUMN_CYCRECORD_DISTANCE);
		Double aveSpeed = rs.getDouble(COLUMN_CYCRECORD_AVE_SPEED);
		Double maxSpeed = rs.getDouble(COLUMN_CYCRECORD_MAX_SPEED);

		return new CyclingRecord(null, userId, deviceId, dateTime, date, startTime, endTime, totalTime, distance, aveSpeed, maxSpeed);
	};
}
