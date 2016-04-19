package com.charilog.api.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.charilog.domain.CyclingRecord;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDownloadCyclingRecord {
	private Integer recordId;
	private Long dateTime;
	private String date;
	private String startTime;
	private String endTime;
	private Long totalTime;
	private Integer distance;
	private Double aveSpeed;
	private Double maxSpeed;

	public ResDownloadCyclingRecord(CyclingRecord e) {
		this.recordId = e.getRecordId();
		this.dateTime = e.getDateTime();
		this.date = e.getDate();
		this.startTime = e.getStartTime();
		this.endTime = e.getEndTime();
		this.totalTime = e.getTotalTime();
		this.distance = e.getDistance();
		this.aveSpeed = e.getAveSpeed();
		this.maxSpeed = e.getMaxSpeed();
	}
}
