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
public class KeyToRecordRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	private static final RowMapper<KeyToRecord> KEY2RECORD_ROW_MAPPER = (rs, i) -> {
//		return new KeyToRecord(
//				rs.getString(COLUMN_KEY2RECORD_KEY),
//				rs.getInt(COLUMN_KEY2RECORD_RECORD_ID),
//				rs.getString(COLUMN_KEY2RECORD_USER_ID));
//	};

	public void deleteByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(TABLE_NAME_KEY2RECORD);
		sql.append(" WHERE ");
		sql.append(COLUMN_KEY2RECORD_USER_ID);
		sql.append(" = :");
		sql.append(COLUMN_KEY2RECORD_USER_ID);

		Map<String, String> params = new HashMap<>();
		params.put(COLUMN_KEY2RECORD_USER_ID, userId);

		int ret = jdbcTemplate.update(sql.toString(), params);
		System.out.println("KEY2RECORD DELETE: " + ret);
	}
}
