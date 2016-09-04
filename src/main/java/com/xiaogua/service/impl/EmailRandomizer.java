package com.xiaogua.service.impl;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

import com.xiaogua.service.InterfaceRandomizer;

public class EmailRandomizer implements InterfaceRandomizer<String> {
	private static final String[] emailSuffixArr = new String[] { "@qq.com", "@163.com", "@126.com", "@sina.com",
			"@139.com", "@sina.cn", "@gmail.com", "@hotmail.com", "@yahoo.com", "@yahoo.com.cn", "@sohu.com",
			"@tom.com", "@163.net", "@mail.com" };
	public static final String alphaNumericStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private int minLength;
	private int maxLength;
	private String emailSuffix;
	private boolean isFixedLength;
	private Random random;
	private StringBuffer sb;

	public EmailRandomizer() {
		super();
		this.random = new Random();
		this.minLength = 4;
		this.maxLength = 4;
		this.isFixedLength = false;
		this.sb = new StringBuffer();
	}

	public EmailRandomizer(int minLength, int maxLength, String emailSuffix) {
		super();
		checkValue(minLength, maxLength);
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.emailSuffix = emailSuffix;
		this.random = new Random();
		if (this.minLength == this.maxLength) {
			this.isFixedLength = true;
		}
		this.sb = new StringBuffer();
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		int len = this.minLength;
		if (!this.isFixedLength) {
			len = RandomUtils.nextInt(this.minLength, this.maxLength + 1);
		}
		this.sb.append(RandomStringUtils.random(len, alphaNumericStr));
		if (this.emailSuffix == null) {
			this.sb.append(emailSuffixArr[this.random.nextInt(emailSuffixArr.length)]);
		} else {
			this.sb.append(this.emailSuffix);
		}
		return sb.toString();
	}

	private void checkValue(int minLength2, int maxLength2) {
		Validate.isTrue(minLength >= 0, "minLength must great than 0");
		Validate.isTrue(maxLength >= minLength, "maxLength must great than minLength");
	}

}
