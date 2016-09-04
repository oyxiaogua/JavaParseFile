package com.xiaogua.service;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.check.LuhnCheck;
import com.xiaogua.service.impl.LuhnRandomizer;

public class TestLuhnRandomizer {

	@Test
	public void testLuhnRandomizer() {
		LuhnRandomizer luhnRandomizer = new LuhnRandomizer();
		String str;
		for (int i = 0; i < 10; i++) {
			str = luhnRandomizer.getRandomValue();
			Assert.assertTrue(LuhnCheck.isValidLuhn(str));
		}
	}
}
