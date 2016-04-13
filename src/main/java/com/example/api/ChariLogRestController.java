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
}
