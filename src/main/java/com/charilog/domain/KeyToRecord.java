package com.charilog.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import static com.charilog.constant.SQLConstants.*;

@Entity
@Table(name = TABLE_NAME_KEY2RECORD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyToRecord {
	@Id
	@Column(name = COLUMN_KEY2RECORD_KEY)
	private String key;
	
	@Column(name = COLUMN_KEY2RECORD_RECORD_ID, nullable = false)
	private Integer recordId;

	@Column(name = COLUMN_KEY2RECORD_USER_ID, nullable = false)
	private String userId;
}
