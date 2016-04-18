package com.charilog.domain;

import javax.persistence.*;

import com.charilog.api.RequestBodyCyclingRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.charilog.constant.SQLConstants.*;

@Entity
@Table(name = TABLE_NAME_CYCLING_RECORD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclingRecord {
	@Id
	@GeneratedValue
	@Column(name = COLUMN_CYCRECORD_RECORD_ID)
	private Integer recordId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable = false, name = COLUMN_USER_USER_ID)
//	private User user;
	
//	@JoinColumn(nullable = false, table = TABLE_NAME_USER, name = COLUMN_USER_USER_ID)
	@Column(name = COLUMN_CYCRECORD_USER_ID, nullable = false)
	private String userId;
	
	@Column(name = COLUMN_CYCRECORD_DEVICE_ID, nullable = false)
	private String deviceId;

	@Column(name = COLUMN_CYCRECORD_DATE_TIME, nullable = false)
	private Long dateTime;

	@Column(name = COLUMN_CYCRECORD_DATE)
	private String date;

	@Column(name = COLUMN_CYCRECORD_START_TIME)
	private String startTime;

	@Column(name = COLUMN_CYCRECORD_END_TIME)
	private String endTime;

	@Column(name = COLUMN_CYCRECORD_TOTAL_TIME)
	private Long totalTime;

	@Column(name = COLUMN_CYCRECORD_DISTANCE)
	private Integer distance;

	@Column(name = COLUMN_CYCRECORD_AVE_SPEED)
	private Double aveSpeed;

	@Column(name = COLUMN_CYCRECORD_MAX_SPEED)
	private Double maxSpeed;

	public CyclingRecord(RequestBodyCyclingRecord request) {
		this.userId = request.getUserId();
		this.deviceId = request.getDeviceId();
		this.dateTime = request.getDateTime();
		this.date = request.getDate();
		this.startTime = request.getStartTime();
		this.endTime = request.getEndTime();
		this.totalTime = request.getTotalTime();
		this.distance = request.getDistance();
		this.aveSpeed = request.getAveSpeed();
		this.maxSpeed = request.getMaxSpeed();
	}
}
