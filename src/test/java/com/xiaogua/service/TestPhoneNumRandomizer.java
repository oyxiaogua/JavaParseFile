package com.xiaogua.service;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.check.PhoneNumCheck;
import com.xiaogua.service.impl.PhoneNumRandomizer;

public class TestPhoneNumRandomizer {

	@Test
	public void testPhoneNumRandomizer() {
		PhoneNumRandomizer phoneRandomizer = new PhoneNumRandomizer(8, 8);
		String str = phoneRandomizer.getRandomValue();
		Assert.assertTrue(PhoneNumCheck.isValidPhoneNum(str));
	}
}
