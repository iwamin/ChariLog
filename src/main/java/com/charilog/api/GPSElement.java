package com.charilog.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPSElement {
	private Long dateTime;
	private Double latitude;
	private Double longitude;
	private Double altitude;
}
