package com.xiaogua.check;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateCheck {
	private static final Logger log = LoggerFactory.getLogger(DateCheck.class);
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static boolean isValidDate(String dateStr) {
		return isValidDate(dateStr, YYYYMMDD);
	}

	public static boolean isValidDate(String dateStr, String formatStr) {
		DateFormat dateFormatter = getDateFormat(formatStr);
		dateFormatter.setLenient(false);
		try {
			dateFormatter.parse(dateStr);
			return true;
		} catch (Exception e) {
			log.error("isValidDate", e);
		}
		return false;
	}

	public static String formatDate(Date date) {
		return formatDate(date, YYYYMMDD);
	}

	public static String formatDate(Date date, String formatStr) {
		DateFormat dateFormatter = getDateFormat(formatStr);
		return dateFormatter.format(date);
	}

	private static DateFormat getDateFormat(String formatStr) {
		DateFormat dateFormatter;
		if (StringUtils.isNotBlank(formatStr)) {
			dateFormatter = new SimpleDateFormat(formatStr);
		} else {
			dateFormatter = new SimpleDateFormat(YYYYMMDD);
		}
		return dateFormatter;
	}
}
