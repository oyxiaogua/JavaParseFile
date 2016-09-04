package com.xiaogua.check;

import org.apache.commons.lang3.StringUtils;

public class ChineseNameCheck {
	private static String chineseNameRegexStr = "[\u4e00-\u9fa5·]";

	/**
	 * 是否是合法的中文姓名
	 * @return
	 */
	public static boolean isValidCnName(String nameStr) {
		if(StringUtils.isBlank(nameStr)){
			return false;
		}
		nameStr = nameStr.replaceAll(chineseNameRegexStr, "");
		if (nameStr.length() > 0) {
			return false;
		}
		return true;
	}
}
