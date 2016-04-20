package com.charilog.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.domain.CyclingRecord;

import static com.charilog.constant.SQLConstants.*;

@Repository
@Transactional
public class CyclingRecordRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<CyclingRecord> CYCLING_RECORD_ROW_MAPPER = (rs, i) -> {
		return new CyclingRecord(
				rs.getInt(COLUMN_CYCRECORD_RECORD_ID),
				rs.getString(COLUMN_CYCRECORD_USER_ID),
				rs.getString(COLUMN_CYCRECORD_DEVICE_ID),
				rs.getLong(COLUMN_CYCRECORD_DATE_TIME),
				rs.getString(COLUMN_CYCRECORD_DATE),
				rs.getString(COLUMN_CYCRECORD_START_TIME),
				rs.getString(COLUMN_CYCRECORD_END_TIME),
				rs.getLong(COLUMN_CYCRECORD_TOTAL_TIME),
				rs.getInt(COLUMN_CYCRECORD_DISTANCE),
				rs.getDouble(COLUMN_CYCRECORD_AVE_SPEED),
				rs.getDouble(COLUMN_CYCRECORD_MAX_SPEED));
	};

	public List<CyclingRecord> findByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(TABLE_NAME_CYCLING_RECORD);
		sql.append(" WHERE ");
		sql.append(COLUMN_CYCRECORD_USER_ID);
		sql.append(" = :");
		sql.append(COLUMN_CYCRECORD_USER_ID);
		SqlParameterSource param = new MapSqlParameterSource().addValue(COLUMN_CYCRECORD_USER_ID, userId);

		List<CyclingRecord> records = jdbcTemplate.query(sql.toString(), param, CYCLING_RECORD_ROW_MAPPER);

		return records;
	}

	public List<CyclingRecord> find(String userId, String deviceId, Long dateTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(TABLE_NAME_CYCLING_RECORD);
		sql.append(" WHERE ");
		sql.append(COLUMN_CYCRECORD_USER_ID);
		sql.append(" = :");
		sql.append(COLUMN_CYCRECORD_USER_ID);
		sql.append(" AND ");
		sql.append(COLUMN_CYCRECORD_DEVICE_ID);
		sql.append(" = :");
		sql.append(COLUMN_CYCRECORD_DEVICE_ID);
		sql.append(" AND ");
		sql.append(COLUMN_CYCRECORD_DATE_TIME);
		sql.append(" = :");
		sql.append(COLUMN_CYCRECORD_DATE_TIME);

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue(COLUMN_CYCRECORD_USER_ID, userId);
		param.addValue(COLUMN_CYCRECORD_DEVICE_ID, deviceId);
		param.addValue(COLUMN_CYCRECORD_DATE_TIME, dateTime);

		return jdbcTemplate.query(sql.toString(), param, CYCLING_RECORD_ROW_MAPPER);
	}
}
