package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;

import static com.example.constant.SQLConstants.*;

@Repository
@Transactional
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		String id = rs.getString(COLUMN_USER_USER_ID);
		String password = rs.getString(COLUMN_USER_PASSWORD);
		return new User(id, password);
	};

//	public User findOneByName(String userId) {
//		SqlParameterSource param = new MapSqlParameterSource().addValue(COLUMN_USER_ID, userId);
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM ");
//		sql.append(TABLE_NAME_USER);
//		sql.append(" WHERE ");
//		sql.append(COLUMN_USER_ID);
//		sql.append(" = :");
//		sql.append(COLUMN_USER_ID);
//
//		List<User> users = jdbcTemplate.query(sql.toString(), param, USER_ROW_MAPPER);
//
//		if (users.isEmpty()) {
//			return null;
//		} else {
//			return users.get(0);
//		}
//	}
}
