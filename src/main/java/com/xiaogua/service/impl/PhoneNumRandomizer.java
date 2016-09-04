package com.xiaogua.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Validate;

import com.xiaogua.service.InterfaceRandomizer;

public class PhoneNumRandomizer implements InterfaceRandomizer<String> {
	private Random random;
	private StringBuffer sb;
	// 最大长度
	private int maxLength = 8;
	// 最小长度
	private int minLength = 1;
	// 是否固定长度
	private boolean isFixedLength = false;
	// 纯数字数据源
	private static final List<Character> numberRandomList = IntStream.range(48, 58).mapToObj(x -> (char) x)
			.collect(Collectors.toList());
	// 电话号码前缀
	private static final String[] phonePrefixArr = new String[] { "130", "131", "132", "155", "156", "186", "185",
			"176", "139", "138", "137", "136", "135", "134", "159", "150", "151", "158", "157", "188", "187", "152",
			"182", "147", "133", "153", "180", "189" };

	public PhoneNumRandomizer() {
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public PhoneNumRandomizer(int minLength, int maxLength) {
		checkValue(minLength, maxLength);
		this.minLength = minLength;
		this.maxLength = maxLength;
		if (this.minLength == this.maxLength) {
			this.isFixedLength = true;
		}
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		sb.append(phonePrefixArr[this.random.nextInt(phonePrefixArr.length)]);
		int randomLen = this.minLength;
		if (!this.isFixedLength) {
			randomLen = this.random.nextInt(this.maxLength - this.minLength + 1) + this.minLength;
		}
		for (int i = 0; i < randomLen; i++) {
			this.sb.append(numberRandomList.get(this.random.nextInt(numberRandomList.size())));
		}
		return this.sb.toString();
	}

	private void checkValue(int minLength, int maxLength) {
		Validate.isTrue(minLength >= 0, "minLength must great than 0");
		Validate.isTrue(maxLength >= minLength, "maxLength must great than minLength");
	}
}
