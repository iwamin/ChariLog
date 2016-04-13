package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CyclingRecord;
import com.example.domain.GPSData;
import com.example.domain.User;
import com.example.service.CyclingRecordService;
import com.example.service.GPSDataService;
import com.example.service.UserService;

@RestController
@RequestMapping("")
public class ChariLogRestController {
	@Autowired
	UserService userService;

	@Autowired
	CyclingRecordService cyclingRecordService;

	@Autowired
	GPSDataService gpsDataService;

	@RequestMapping(value = "account", method = RequestMethod.GET)
	List<User> getUserList() {
		return userService.findAll();
	}

	@RequestMapping(value = "account", method = RequestMethod.POST)
	ResponseEntity<User> create(@RequestBody RequestBodyUser requestBody) {
		User user = new User(requestBody.getUserId(), requestBody.getPassword());
		ResponseEntity<User> response;

		if (userService.isExisting(user)) {
			response = new ResponseEntity<>(user, null, HttpStatus.CONFLICT);
		} else {
			User created = userService.create(user);
			response = new ResponseEntity<>(created, null, HttpStatus.CREATED);
		}
		return response;
	}

	@RequestMapping(value = "record", method = RequestMethod.POST)
	ResponseEntity<CyclingRecord> uploadRecord(@RequestBody RequestBodyCyclingRecord requestBody) {
		CyclingRecord record = cyclingRecordService.create(requestBody);
		ResponseEntity<CyclingRecord> response = new ResponseEntity<>(record, null, HttpStatus.ACCEPTED);
		return response;
	}

	@RequestMapping(value = "record", method = RequestMethod.GET)
	List<CyclingRecord> downloadRecord() {
		return cyclingRecordService.findAll();
	}

	@RequestMapping(value = "gps", method = RequestMethod.POST)
	ResponseEntity<GPSData> uploadGPSData(@RequestBody RequestBodyGPSData requestBody) {
		System.out.println(requestBody.toString());
		GPSData created = gpsDataService.create(requestBody);
		System.out.println(created.toString());
		ResponseEntity<GPSData> response = new ResponseEntity<>(created, null, HttpStatus.ACCEPTED);
		return response;
	}

	@RequestMapping(value = "gps", method = RequestMethod.GET)
	List<GPSData> downloadGPSData() {
		return gpsDataService.findAll();
	}

	@RequestMapping(value = "test", method = RequestMethod.POST)
	void testPost(@RequestBody String requestBody, @RequestHeader("Content-Type") String type) {
		System.out.println("TEST:\n" + type + "\n" + requestBody);
	}
}
