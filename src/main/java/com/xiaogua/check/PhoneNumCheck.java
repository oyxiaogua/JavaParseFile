package com.xiaogua.check;

import org.apache.commons.lang3.StringUtils;

public class PhoneNumCheck {
	private static String phoneNumCheckRegexStr = "^1[0-9]{10}$";

	public static boolean isValidPhoneNum(String phoneNumber) {
		if (StringUtils.isBlank(phoneNumber)) {
			return false;
		}
		if (phoneNumber.charAt(0) != '1') {
			return false;
		}
		if (!phoneNumber.matches(phoneNumCheckRegexStr)) {
			return false;
		}
		return true;
	}
}
