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
import com.example.domain.KeyToRecord;
import com.example.domain.User;
import com.example.lib.CommonLib;
import com.example.service.CyclingRecordService;
import com.example.service.GPSDataService;
import com.example.service.KeyToRecordService;
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
	
	@Autowired
	KeyToRecordService keyToRecordService;

	// ユーザーリスト取得(※デバッグ用)
	@RequestMapping(value = "account", method = RequestMethod.GET)
	List<User> getUserList() {
		return userService.findAll();
	}

	// ユーザー作成
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

	// 走行記録1件登録
	@RequestMapping(value = "record", method = RequestMethod.POST)
	ResponseEntity<ResponseBodyCyclingRecord> uploadRecord(@RequestBody RequestBodyCyclingRecord requestBody) {
		// 走行記録を出たベースに登録
		CyclingRecord record = cyclingRecordService.create(requestBody);

		// GPSデータ登録用のKeyを作成し、JSONで応答する
		HttpStatus httpStatus = HttpStatus.ACCEPTED;
		String key = "";
		if (record != null) {
			key = CommonLib.encryptSHA256(record.getUserId() + record.getRecordId());
			// key管理用テーブルに登録しておく
			KeyToRecord entity = new KeyToRecord(key, record.getRecordId());
			keyToRecordService.create(entity);
		} else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		ResponseBodyCyclingRecord jsonBody = new ResponseBodyCyclingRecord(key);

		ResponseEntity<ResponseBodyCyclingRecord> response
				= new ResponseEntity<>(jsonBody, null, httpStatus);
		return response;
	}

	// 走行記録リストを取得(※デバッグ用)
	@RequestMapping(value = "record", method = RequestMethod.GET)
	List<CyclingRecord> downloadRecord() {
		return cyclingRecordService.findAll();
	}

	// GPSデータ(走行記録1件分)を登録
	@RequestMapping(value = "gps", method = RequestMethod.POST)
	ResponseEntity<GPSData> uploadGPSData(@RequestBody RequestBodyGPSData requestBody) {
		System.out.println(requestBody.toString());

		// key管理用テーブルからそのkeyに対応するrecordIdを取得する
		KeyToRecord result = keyToRecordService.find(requestBody.getKey());
		if (result == null) {
			// 不明なkeyの場合、認証失敗として終了する
			return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
		}

		// GPSデータを記録する
		Integer recordId = result.getRecordId();
		for (GPSElement element : requestBody.getData()) {
			GPSData created = gpsDataService.create(element, recordId);
			if (created == null) {
				return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		ResponseEntity<GPSData> response = new ResponseEntity<>(null, null, HttpStatus.ACCEPTED);
		return response;
	}

	// GPSデータ(走行記録1件分)を取得
	@RequestMapping(value = "gps", method = RequestMethod.GET)
	List<GPSData> downloadGPSData() {
		return gpsDataService.findAll();
	}

	// JSON内容表示(※デバッグ用)
	@RequestMapping(value = "test", method = RequestMethod.POST)
	void testPost(@RequestBody String requestBody, @RequestHeader("Content-Type") String type) {
		System.out.println("TEST:\n" + type + "\n" + requestBody);
	}
}
