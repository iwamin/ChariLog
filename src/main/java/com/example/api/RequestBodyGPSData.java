package com.example.api;

import lombok.Data;

@Data
public class RequestBodyGPSData {
	private Long dateTime;
	private Double latitude;
	private Double longitude;
	private Double altitude;
}
