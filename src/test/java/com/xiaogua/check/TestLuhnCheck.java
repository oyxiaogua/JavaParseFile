package com.xiaogua.check;

import org.junit.Assert;
import org.junit.Test;

public class TestLuhnCheck {

	@Test
	public void testLuhnCheck() {
		String code = "5432123456788881";
		boolean rtnVal = LuhnCheck.isValidLuhn(code);
		Assert.assertTrue(rtnVal);

		String calCode = "543212345678888";
		String validCode = LuhnCheck.calculateLuhn(calCode);
		Assert.assertEquals("1", validCode);

		calCode = "63214140980000000";
		validCode = LuhnCheck.calculateLuhn(calCode);
		Assert.assertEquals("5", validCode);
	}
}
