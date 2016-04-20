package com.charilog.api.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqDownloadGPSData {
	private String userId;
	private String password;
	private Integer recordId;
}
