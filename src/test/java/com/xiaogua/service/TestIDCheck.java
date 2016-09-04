package com.xiaogua.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.check.IDCheck;

public class TestIDCheck {
	private static final Logger log = LoggerFactory.getLogger(TestIDCheck.class);

	@Test
	public void testIDCheck() {
		String str = "52272819751107905";
		String checkCode = IDCheck.getIDCheckCode(str);
		log.info(checkCode);
	}
}
