package com.example.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cycling_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclingRecord {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private Integer userId;
	
	@Column(nullable = false)
	private String deviceId;

	@Column(nullable = false)
	private Long dateTime;

	private String date;

	private String startTime;

	private String endTime;

	private Long totalTime;

	private Integer distance;

	private Double aveSpeed;

	private Double maxSpeed;
}
