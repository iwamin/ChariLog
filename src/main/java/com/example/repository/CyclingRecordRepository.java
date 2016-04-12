package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CyclingRecord;

@Repository
@Transactional
public class CyclingRecordRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<CyclingRecord> rowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		Integer userId = rs.getInt("user_id");
		String deviceId = rs.getString("device_id");
		Long dateTime = rs.getLong("date_time");
		String date = rs.getString("date");
		String startTime = rs.getString("start_time");
		String endTime = rs.getString("end_time");
		Long totalTime = rs.getLong("total_time");
		Integer distance = rs.getInt("distance");
		Double aveSpeed = rs.getDouble("ave_speed");
		Double maxSpeed = rs.getDouble("max_speed");

		return new CyclingRecord(id, userId, deviceId, dateTime, date, startTime, endTime, totalTime, distance, aveSpeed, maxSpeed);
	};
}
