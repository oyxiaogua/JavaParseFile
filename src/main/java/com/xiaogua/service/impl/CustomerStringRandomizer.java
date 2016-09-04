package com.xiaogua.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

import com.xiaogua.service.InterfaceRandomizer;

/**
 * 限定长度的随机字符串
 */
public class CustomerStringRandomizer implements InterfaceRandomizer<String> {
	private Random random;
	private StringBuffer sb;
	// 最大长度
	private int maxLength = 32;
	// 最小长度
	private int minLength = 1;
	// 是否固定长度
	private boolean isFixedLength = false;
	// 随机数据源
	private List<Character> randomDataSourceList;
	// 纯字母数据源
	private static final List<Character> letterRandomList = IntStream.range(65, 123).mapToObj(x -> (char) x)
			.filter(Character::isLetter).collect(Collectors.toList());
	// 纯数字数据源
	private static final List<Character> numberRandomList = IntStream.range(48, 58).mapToObj(x -> (char) x)
			.collect(Collectors.toList());
	// 数字字母数据源
	private static final List<Character> alphaNumericRandomList = IntStream.range(48, 123)
			.filter(c -> (Character.isLetter(c) || Character.isDigit(c))).mapToObj(x -> (char) x)
			.collect(Collectors.toList());

	// ascii数据源
	private static final List<Character> asciiRandomList = IntStream.range(32, 127).mapToObj(x -> (char) x)
			.collect(Collectors.toList());

	public CustomerStringRandomizer() {
		this.randomDataSourceList = letterRandomList;
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public CustomerStringRandomizer(List<Character> randomList, int minLength, int maxLength) {
		if (randomList == null || randomList.size() == 0) {
			throw new IllegalArgumentException();
		}
		checkValue(minLength, maxLength);
		this.minLength = minLength;
		this.maxLength = maxLength;
		if (this.minLength == this.maxLength) {
			isFixedLength = true;
		}
		this.randomDataSourceList = new ArrayList<Character>(randomList);
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public static CustomerStringRandomizer aNewAlphaRandomizer(int minLength, int maxLength) {
		return new CustomerStringRandomizer(true, false, false, minLength, maxLength);
	}

	public static CustomerStringRandomizer aNewNumberRandomizer(int minLength, int maxLength) {
		return new CustomerStringRandomizer(false, true, false, minLength, maxLength);
	}

	public static CustomerStringRandomizer aNewAlphaNumericRandomizer(int minLength, int maxLength) {
		return new CustomerStringRandomizer(true, true, false, minLength, maxLength);
	}

	public static CustomerStringRandomizer aNewAsciiRandomizer(int minLength, int maxLength) {
		return new CustomerStringRandomizer(false, false, true, minLength, maxLength);
	}

	public static CustomerStringRandomizer aNewCustRandomizer(List<Character> randomList, int minLength,
			int maxLength) {
		return new CustomerStringRandomizer(randomList, minLength, maxLength);
	}

	public static CustomerStringRandomizer aNewCustRandomizer(String randomValue, int minLength, int maxLength) {
		String[] strList = randomValue.split("");
		List<Character> charList = new ArrayList<Character>(strList.length);
		for (String str : strList) {
			charList.add(str.toCharArray()[0]);
		}
		return new CustomerStringRandomizer(charList, minLength, maxLength);
	}

	public CustomerStringRandomizer(boolean isLetter, boolean isNumber, boolean isAscii, int minLength, int maxLength) {
		checkValue(minLength, maxLength);
		this.minLength = minLength;
		this.maxLength = maxLength;
		if (this.minLength == this.maxLength) {
			isFixedLength = true;
		}
		this.random = new Random();
		this.sb = new StringBuffer(16);
		if (isAscii) {
			randomDataSourceList = asciiRandomList;
			return;
		}
		if (isLetter && isNumber) {
			randomDataSourceList = alphaNumericRandomList;
			return;
		}
		if (isLetter) {
			randomDataSourceList = letterRandomList;
			return;
		}
		if (isNumber) {
			randomDataSourceList = numberRandomList;
			return;
		}
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		int randomLen = this.minLength;
		if (!this.isFixedLength) {
			randomLen = RandomUtils.nextInt(this.minLength, this.maxLength + 1);
		}
		for (int i = 0; i < randomLen; i++) {
			this.sb.append(this.randomDataSourceList.get(this.random.nextInt(this.randomDataSourceList.size())));
		}
		return this.sb.toString();
	}

	private void checkValue(int minLength, int maxLength) {
		Validate.isTrue(minLength >= 0, "minLength must great than 0");
		Validate.isTrue(maxLength >= minLength, "maxLength must great than minLength");
	}

}
