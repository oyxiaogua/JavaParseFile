package com.xiaogua.check;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuhnCheck {
	private static final Logger log = LoggerFactory.getLogger(LuhnCheck.class);
	private static LuhnCheckDigit luhn = new LuhnCheckDigit();

	public static boolean isValidLuhn(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}
		return luhn.isValid(code);
	}

	public static String calculateLuhn(String panNUmber) {
		String checkdigit = null;
		try {
			checkdigit = luhn.calculate(panNUmber);
		} catch (Exception e) {
			log.error("calculateLuhn", e);
		}
		return checkdigit;
	}
}
