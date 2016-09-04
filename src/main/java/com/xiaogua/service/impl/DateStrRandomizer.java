package com.xiaogua.service.impl;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.xiaogua.check.DateCheck;
import com.xiaogua.service.InterfaceRandomizer;

public class DateStrRandomizer implements InterfaceRandomizer<String> {
	private Date startDate;
	private Date endDate;
	private boolean isFixedDate;
	private String dateFormatStr = DateCheck.YYYY_MM_DD_HH_MM_SS;
	private long startDateVal;
	private long endDateVal;

	public DateStrRandomizer() {
		super();
		this.startDate = new Date();
		this.endDate = this.startDate;
		this.startDateVal = this.startDate.getTime();
		this.endDateVal = this.startDateVal;
		this.isFixedDate = true;
	}

	public DateStrRandomizer(Date startDate, Date endDate, String dateFormatStr) {
		super();
		checkValue(startDate, endDate);
		this.startDate = startDate;
		this.endDate = endDate;
		if (StringUtils.isNoneBlank(dateFormatStr)) {
			this.dateFormatStr = dateFormatStr;
		}
		this.startDateVal = this.startDate.getTime();
		this.endDateVal = this.endDate.getTime();
		if (this.startDateVal == this.endDateVal) {
			this.isFixedDate = true;
		}
	}

	public String getRandomValue() {
		Date randomDate = this.startDate;
		if (!isFixedDate) {
			randomDate = new Date(RandomUtils.nextLong(this.startDate.getTime(), this.endDate.getTime() + 1));
		}
		return DateCheck.formatDate(randomDate, dateFormatStr);
	}

	private void checkValue(Date startDate, Date endDate) {
		Validate.isTrue(endDate.compareTo(startDate) >= 0, "startDateStr less than endDateStr");
	}
}
