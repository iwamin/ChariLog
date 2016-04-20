package com.charilog.repository;

import static com.charilog.constant.SQLConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GPSDataRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
//	private static final RowMapper<GPSData> GPS_DATA_ROW_MAPPER = (rs, i) -> {
//		return new GPSData(
//				rs.getInt(COLUMN_GPSDATA_GPS_ID),
//				rs.getInt(COLUMN_GPSDATA_RECORD_ID),
//				rs.getLong(COLUMN_GPSDATA_DATE_TIME),
//				rs.getDouble(COLUMN_GPSDATA_LATITUDE),
//				rs.getDouble(COLUMN_GPSDATA_LONGITUDE),
//				rs.getDouble(COLUMN_GPSDATA_ALTITUDE));
//	};

	public void deleteByRecordId(Integer recordId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(TABLE_NAME_GPS_DATA);
		sql.append(" WHERE ");
		sql.append(COLUMN_GPSDATA_RECORD_ID);
		sql.append(" = :");
		sql.append(COLUMN_GPSDATA_RECORD_ID);

		Map<String, Integer> params = new HashMap<>();
		params.put(COLUMN_GPSDATA_RECORD_ID, recordId);

		int ret = jdbcTemplate.update(sql.toString(), params);
		System.out.println("GPD_DATA DELETE: " + ret);
	}
}
