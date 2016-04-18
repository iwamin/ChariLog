package com.charilog.domain;

import javax.persistence.*;

import com.charilog.api.param.GPSElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.charilog.constant.SQLConstants.*;

@Entity
@Table(name = TABLE_NAME_GPS_DATA)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPSData {
	@Id
	@GeneratedValue
	@Column(name = COLUMN_GPSDATA_GPS_ID)
	private Integer gpsId;

	@Column(name = COLUMN_GPSDATA_RECORD_ID, nullable = false)
	private Integer recordId;

	@Column(name = COLUMN_GPSDATA_DATE_TIME, nullable = false)
	private Long dateTime;

	@Column(name = COLUMN_GPSDATA_LATITUDE, nullable = false)
	private Double latitude;

	@Column(name = COLUMN_GPSDATA_LONGITUDE, nullable = false)
	private Double longitude;

	@Column(name = COLUMN_GPSDATA_ALTITUDE, nullable = false)
	private Double altitude;

	public GPSData(GPSElement request) {
		this.dateTime = request.getDateTime();
		this.latitude = request.getLatitude();
		this.longitude = request.getLongitude();
		this.altitude = request.getAltitude();
	}
}
