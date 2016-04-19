package com.charilog.api.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqUploadCyclingRecord {
	private String userId;
	private String password;
	private String deviceId;
	private Long dateTime;
	private String date;
	private String startTime;
	private String endTime;
	private Long totalTime;
	private Integer distance;
	private Double aveSpeed;
	private Double maxSpeed;
}
