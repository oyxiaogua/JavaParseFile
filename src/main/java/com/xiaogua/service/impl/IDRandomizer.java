package com.xiaogua.service.impl;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.check.DateCheck;
import com.xiaogua.check.IDCheck;
import com.xiaogua.service.InterfaceRandomizer;

public class IDRandomizer implements InterfaceRandomizer<String> {
	private static final Logger log = LoggerFactory.getLogger(IDRandomizer.class);
	private Random random;
	// 开始年
	private String startDateStr;
	// 开始月
	private String endDateStr;
	// 性别(1男0女-1不限制)
	private int sex = 1;
	private boolean isFixedDate = false;
	private Date startDate;
	private Date endDate;
	private StringBuffer sb;

	public String getRandomValue() {
		this.sb.setLength(0);
		this.sb.append(IDCheck.provArr[this.random.nextInt(IDCheck.provArr.length)]);
		this.sb.append(formatRandomValue(RandomUtils.nextInt(1, 19)));
		this.sb.append(formatRandomValue(RandomUtils.nextInt(1, 29)));
		if (this.isFixedDate) {
			this.sb.append(this.startDateStr);
		} else {
			this.sb.append(DateCheck
					.formatDate(new Date(RandomUtils.nextLong(this.startDate.getTime(), this.endDate.getTime() + 1))));
		}
		this.sb.append(formatRandomValue(RandomUtils.nextInt(1, 100)));
		if (this.sex == -1) {
			this.sex = this.random.nextInt(2);
		}
		this.sb.append(sex);
		String str = this.sb.toString();
		this.sb.append(IDCheck.getIDCheckCode(str));
		return this.sb.toString();
	}

	public IDRandomizer() {
		this.startDateStr = DateCheck.formatDate(new Date());
		this.endDateStr = this.startDateStr;
		this.isFixedDate = true;
		this.random = new Random();
		this.sb = new StringBuffer(32);
	}

	public IDRandomizer(String startDateStr, String endDateStr, int sex) {
		super();
		checkValue(startDateStr, endDateStr, sex);
		this.startDateStr = startDateStr;
		this.endDateStr = endDateStr;
		this.sex = sex;
		this.random = new Random();
		if (this.startDateStr.equals(this.endDateStr)) {
			this.isFixedDate = true;
		}
		this.sb = new StringBuffer(32);
	}

	private String formatRandomValue(int value) {
		if (value > 9) {
			return Integer.toString(value);
		}
		return String.format("%02d", value);
	}

	private void checkValue(String startDateStr, String endDateStr, int sex) {
		Validate.isTrue(DateCheck.isValidDate(startDateStr), "startDateStr must be yyyyMMdd format");
		Validate.isTrue(DateCheck.isValidDate(endDateStr), "endDateStr must be yyyyMMdd format");
		try {
			startDate = org.apache.commons.lang3.time.DateUtils.parseDate(startDateStr, DateCheck.YYYYMMDD);
			endDate = org.apache.commons.lang3.time.DateUtils.parseDate(endDateStr, DateCheck.YYYYMMDD);
		} catch (Exception e) {
			log.error("checkValue", e);
		}
		Validate.isTrue(endDate.compareTo(startDate) >= 0, "startDateStr less than endDateStr");
		Validate.isTrue(sex >= -1 && sex <= 1, "sex value range [-1,1]");
	}
}
