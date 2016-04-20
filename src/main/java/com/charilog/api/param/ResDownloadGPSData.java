package com.charilog.api.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDownloadGPSData {
	private Integer recordId;
	private GPSElement[] data;
}
