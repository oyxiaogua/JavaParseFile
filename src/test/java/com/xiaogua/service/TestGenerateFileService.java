package com.xiaogua.service;

import java.util.Date;

import org.junit.Test;

import com.xiaogua.check.DateCheck;
import com.xiaogua.service.impl.ChineseRandomNameRandomizer;
import com.xiaogua.service.impl.CustomerStringRandomizer;
import com.xiaogua.service.impl.DateStrRandomizer;
import com.xiaogua.service.impl.EmailRandomizer;
import com.xiaogua.service.impl.GenerateFileServiceImpl;
import com.xiaogua.service.impl.IDRandomizer;
import com.xiaogua.service.impl.PhoneNumRandomizer;

public class TestGenerateFileService {

	@Test
	public void testGenerateFile() throws Exception {
		String startDateStr = "2016-1-1 0:0:0";
		String endDateStr = "2016-12-31 23:59:59";
		String idStartDateStr = "19890102";
		String idEndDateStr = "20161231";
		Date startDate = org.apache.commons.lang3.time.DateUtils.parseDate(startDateStr, DateCheck.YYYY_MM_DD_HH_MM_SS);
		Date endDate = org.apache.commons.lang3.time.DateUtils.parseDate(endDateStr, DateCheck.YYYY_MM_DD_HH_MM_SS);
		// 随机姓名
		InterfaceRandomizer<String> nameRandomizer = ChineseRandomNameRandomizer.aNewMixNameRandomizer(2, 2, -1);
		// 随机时间
		InterfaceRandomizer<String> dateRandomizer = new DateStrRandomizer(startDate, endDate,
				DateCheck.YYYY_MM_DD_HH_MM_SS);
		// 随机电话号码
		InterfaceRandomizer<String> phoneRandomizer = new PhoneNumRandomizer(8, 8);
		// 随机email
		InterfaceRandomizer<String> emailRandomizer = new EmailRandomizer(8, 16, null);
		// 随机id
		InterfaceRandomizer<String> idRandomizer = new IDRandomizer(idStartDateStr, idEndDateStr, -1);
		// 随机字符串
		InterfaceRandomizer<String> strRandomizer = CustomerStringRandomizer.aNewAlphaNumericRandomizer(2, 3);
		final char separator = ',';
		InterfaceGenerateFileService fileService = new GenerateFileServiceImpl();
		String filePath = "e:/test_tmp/0904/randomFile.txt";
		String encoding = "UTF-8";
		int countNum = 500000;
		fileService.generateFile(nameRandomizer, dateRandomizer, phoneRandomizer, emailRandomizer, idRandomizer,
				strRandomizer, filePath, encoding, separator, countNum);
	}
}
