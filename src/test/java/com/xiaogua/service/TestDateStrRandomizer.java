package com.xiaogua.service;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.check.DateCheck;
import com.xiaogua.service.impl.DateStrRandomizer;

public class TestDateStrRandomizer {
	private static final Logger log = LoggerFactory.getLogger(TestDateStrRandomizer.class);

	@Test
	public void testDateStrRandomizer() throws Exception {
		String startDateStr = "2016-2-1 11:12:13";
		String endDateStr = "2016-2-10 11:12:13";
		Date startDate = org.apache.commons.lang3.time.DateUtils.parseDate(startDateStr, DateCheck.YYYY_MM_DD_HH_MM_SS);
		Date endDate = org.apache.commons.lang3.time.DateUtils.parseDate(endDateStr, DateCheck.YYYY_MM_DD_HH_MM_SS);
		DateStrRandomizer dateStrRandomizer = new DateStrRandomizer(startDate, endDate, null);
		String str;
		for (int i = 0; i < 10; i++) {
			str = dateStrRandomizer.getRandomValue();
			log.info(str);
		}
	}
}
