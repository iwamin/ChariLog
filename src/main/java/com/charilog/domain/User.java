package com.charilog.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import static com.charilog.constant.SQLConstants.*;

@Entity
@Table(name = TABLE_NAME_USER)
//@ToString(exclude = "records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@Column(name = COLUMN_USER_USER_ID)
	private String userId;
	
	@Column(name = COLUMN_USER_PASSWORD, nullable = false)
	@JsonIgnore
	private String password;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//	@JsonIgnore
//	private List<CyclingRecord> records;
//	
//	public User(String userId, String password) {
//		this.userId = userId;
//		this.password = password;
//	}
}
