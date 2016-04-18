package com.charilog.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charilog.api.param.GPSElement;
import com.charilog.api.param.ReqGetCyclingRecord;
import com.charilog.api.param.ReqPostCyclingRecord;
import com.charilog.api.param.ReqPostGPSData;
import com.charilog.api.param.ReqPostUser;
import com.charilog.api.param.ResPostCyclingRecord;
import com.charilog.domain.CyclingRecord;
import com.charilog.domain.GPSData;
import com.charilog.domain.KeyToRecord;
import com.charilog.domain.User;
import com.charilog.lib.CommonLib;
import com.charilog.service.CyclingRecordService;
import com.charilog.service.GPSDataService;
import com.charilog.service.KeyToRecordService;
import com.charilog.service.UserService;

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
//	@RequestMapping(value = "account", method = RequestMethod.GET)
//	List<User> getUserList() {
//		return userService.findAll();
//	}

	// ユーザー作成
	@RequestMapping(value = "account", method = RequestMethod.POST)
	ResponseEntity<User> create(@RequestBody ReqPostUser requestBody) {
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
	ResponseEntity<ResPostCyclingRecord> uploadRecord(@RequestBody ReqPostCyclingRecord requestBody) {
		// ユーザー情報を認証する
		User requestUser = new User(requestBody.getUserId(), requestBody.getPassword());
		if (!userService.authenticate(requestUser)) {
			// 認証失敗の場合は、UNAUTHORIZEDを応答し、終了する。
			return new ResponseEntity<ResPostCyclingRecord>(null, null, HttpStatus.UNAUTHORIZED);
		}

		// 走行記録テーブルに登録する
		CyclingRecord record = cyclingRecordService.create(requestBody);
		if (record == null) {
			// 登録できなかった場合は、NOT_ACCEPTABLEを応答し、終了する。
			return new ResponseEntity<ResPostCyclingRecord>(null, null, HttpStatus.NOT_ACCEPTABLE);
		}

		// GPSデータ登録用Keyを作成する
		String key = CommonLib.encryptSHA256(record.getUserId() + record.getRecordId());

		// 作成したkeyをkey管理用テーブルに登録しておく
		KeyToRecord entity = new KeyToRecord(key, record.getRecordId(), record.getUserId());
		KeyToRecord registered = keyToRecordService.register(entity);
		if (registered == null) {
			// 登録できなかった場合は、NOT_ACCEPTABLEを応答し、終了する。
			return new ResponseEntity<ResPostCyclingRecord>(null, null, HttpStatus.NOT_ACCEPTABLE);
		}

		// BodyにGPSデータ登録用Keyを格納して、ACCEPTEDを応答する。
		ResPostCyclingRecord jsonBody = new ResPostCyclingRecord(key);
		return new ResponseEntity<ResPostCyclingRecord>(jsonBody, null, HttpStatus.ACCEPTED);
	}

	// 指定されたユーザーの走行記録を取得
	@RequestMapping(value = "record", method = RequestMethod.GET)
	ResponseEntity<List<CyclingRecord>> downloadRecord(@RequestBody ReqGetCyclingRecord requestBody) {
		// ユーザー情報を認証する
		User requestUser = new User(requestBody.getUserId(), requestBody.getPassword());
		if (!userService.authenticate(requestUser)) {
			// 認証失敗の場合は、UNAUTHORIZEDを応答し、終了する。
			return new ResponseEntity<List<CyclingRecord>>(null, null, HttpStatus.UNAUTHORIZED);
		}

		// 指定されたユーザーの走行記録を取得する
		List<CyclingRecord> records = cyclingRecordService.findByUserId(requestUser.getUserId());

		return new ResponseEntity<List<CyclingRecord>>(records, null, HttpStatus.OK);
	}
	

	// 走行記録リストを取得(※デバッグ用)
	@RequestMapping(value = "record2", method = RequestMethod.GET)
	List<CyclingRecord> findAll() {
		return cyclingRecordService.findAll();
	}

	// GPSデータ(走行記録1件分)を登録
	@RequestMapping(value = "gps", method = RequestMethod.POST)
	ResponseEntity<GPSData> uploadGPSData(@RequestBody ReqPostGPSData requestBody) {
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
				return new ResponseEntity<>(null, null, HttpStatus.NOT_ACCEPTABLE);
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
