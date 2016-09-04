package com.xiaogua.check;

import org.apache.commons.lang3.StringUtils;

public class EntpCodeCheck {
	public static String entpCodeDataStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static int[] entpCodeWeight = { 3, 7, 9, 10, 5, 8, 4, 2 };
	private static String entpCodeRegexStr = "^([0-9A-Z]){8}-[0-9|X]$";

	/**
	 * 组织机构合法性检查
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isValidEntpCode(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}
		if (code.length() != 10) {
			return false;
		}
		if (!code.matches(entpCodeRegexStr)) {
			return false;
		}
		return getEntpCodeCheckCode(code).equals(String.valueOf(code.charAt(9)));
	}

	/**
	 * 获取检验码
	 * 
	 * @param code
	 * @return
	 */
	public static String getEntpCodeCheckCode(String code) {
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			sum += entpCodeDataStr.indexOf(String.valueOf(code.charAt(i))) * entpCodeWeight[i];
		}
		int c9 = 11 - (sum % 11);
		String sc9 = String.valueOf(c9);
		if (11 == c9) {
			sc9 = "0";
		} else if (10 == c9) {
			sc9 = "X";
		}
		return sc9;
	}
}
