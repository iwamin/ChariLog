package com.charilog.api.param;

import com.charilog.domain.GPSData;

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

	public GPSElement(GPSData e) {
		this.dateTime = e.getDateTime();
		this.latitude = e.getLatitude();
		this.longitude = e.getLongitude();
		this.altitude = e.getAltitude();
	}
}
