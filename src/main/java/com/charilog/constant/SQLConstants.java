package com.charilog.constant;

public class SQLConstants {
	public static final String TABLE_NAME_USER = "users";
	public static final String COLUMN_USER_USER_ID = "user_id";
	public static final String COLUMN_USER_PASSWORD = "password";

	public static final String TABLE_NAME_CYCLING_RECORD = "cycling_record";
	public static final String COLUMN_CYCRECORD_RECORD_ID = "record_id";
	public static final String COLUMN_CYCRECORD_USER_ID = "user_id";
	public static final String COLUMN_CYCRECORD_DEVICE_ID = "device_id";
	public static final String COLUMN_CYCRECORD_DATE_TIME = "date_time";
	public static final String COLUMN_CYCRECORD_DATE = "date";
	public static final String COLUMN_CYCRECORD_START_TIME = "start_time";
	public static final String COLUMN_CYCRECORD_END_TIME = "end_time";
	public static final String COLUMN_CYCRECORD_TOTAL_TIME = "total_time";
	public static final String COLUMN_CYCRECORD_DISTANCE = "distance";
	public static final String COLUMN_CYCRECORD_AVE_SPEED = "ave_speed";
	public static final String COLUMN_CYCRECORD_MAX_SPEED = "max_speed";

	public static final String TABLE_NAME_GPS_DATA = "gps_data";
	public static final String COLUMN_GPSDATA_GPS_ID = "gps_id";
	public static final String COLUMN_GPSDATA_RECORD_ID = "record_id";
	public static final String COLUMN_GPSDATA_DATE_TIME = "date_time";
	public static final String COLUMN_GPSDATA_LATITUDE = "latitude";
	public static final String COLUMN_GPSDATA_LONGITUDE = "longitude";
	public static final String COLUMN_GPSDATA_ALTITUDE = "altitude";
	
	public static final String TABLE_NAME_KEY2RECORD = "key_to_record";
	public static final String COLUMN_KEY2RECORD_KEY = "key";
	public static final String COLUMN_KEY2RECORD_RECORD_ID = "record_id";
	public static final String COLUMN_KEY2RECORD_USER_ID = "user_id";
}
