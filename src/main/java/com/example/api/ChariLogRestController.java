package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CyclingRecord;
import com.example.domain.User;
import com.example.service.CyclingRecordService;
import com.example.service.UserService;

@RestController
@RequestMapping("")
public class ChariLogRestController {
	@Autowired
	UserService userService;

	@Autowired
	CyclingRecordService cyclingRecordService;

	@RequestMapping(method = RequestMethod.GET)
	List<User> getUserList() {
		return userService.findAll();
	}
	
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	ResponseEntity<User> create(@RequestBody User user) {
		ResponseEntity<User> response;

		if (userService.isExisting(user)) {
			response = new ResponseEntity<>(null, null, HttpStatus.CONFLICT);
		} else {
			userService.create(user);
			response = new ResponseEntity<>(null, null, HttpStatus.CREATED);
		}

		return response;
	}
	
	@RequestMapping(value = "record", method = RequestMethod.POST)
	ResponseEntity<CyclingRecord> uploadRecord(@RequestBody CyclingRecord record) {
		ResponseEntity<CyclingRecord> response = new ResponseEntity<>(null, null, HttpStatus.ACCEPTED);

		cyclingRecordService.create(record);
		return response;
	}

	@RequestMapping(value = "record", method = RequestMethod.GET)
	List<CyclingRecord> downloadRecord() {
		return cyclingRecordService.findAll();
	}
}
