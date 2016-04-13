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

@Repository
@Transactional
public class UserRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String password = rs.getString("password");
		return new User(id, name, password);
	};
	
	public User findOneByName(String name) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		
		List<User> users = jdbcTemplate.query(
				"SELECT id, name, password FROM users WHERE name = :name",
				param,
				USER_ROW_MAPPER
				);
		
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}
	

}
