package com.xiaogua.service;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.check.EmailCheck;
import com.xiaogua.service.impl.EmailRandomizer;

public class TestEmailRandomizer {

	@Test
	public void testEmailRandomizer() {
		EmailRandomizer emailRandomizer = new EmailRandomizer(4, 8, null);
		String str;
		for (int i = 0; i < 10; i++) {
			str = emailRandomizer.getRandomValue();
			Assert.assertTrue(EmailCheck.isValidEmail(str));
		}
	}
}
