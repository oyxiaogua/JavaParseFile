package com.xiaogua.service.impl;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.xiaogua.check.LuhnCheck;
import com.xiaogua.service.InterfaceRandomizer;

public class LuhnRandomizer implements InterfaceRandomizer<String> {
	private final static int[] randomLen = new int[] { 15, 18 };
	private Random random;
	private StringBuffer sb;

	public LuhnRandomizer() {
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		int lenIndex = this.random.nextInt(2);
		String luchCode = RandomStringUtils.randomNumeric(randomLen[lenIndex]);
		this.sb.append(luchCode);
		this.sb.append(LuhnCheck.calculateLuhn(luchCode));
		return this.sb.toString();
	}

}
