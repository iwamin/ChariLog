package com.charilog.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyGPSData {
	private String key;
	private GPSElement[] data;
}
