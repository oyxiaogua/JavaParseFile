package com.xiaogua.check;

import org.apache.commons.lang3.StringUtils;

public class EmailCheck {
	private static String emailRegexStr = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

	/**
	 * 1. 必须包含一个并且只有一个符号"@"<br/>
	 * 2. 第一个字符不得是"@"或者"." <br/>
	 * 3. 不允许出现"@."或者.@ <br/>
	 * 4. 结尾不得是字符"@"或者"." <br/>
	 * 5. 允许"@"前的字符中出现"＋" <br/>
	 * 6. 不允许"＋"在最前面，或者"＋@" <br/>
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		if (email.indexOf("@") < 0) {
			return false;
		}
		return email.matches(emailRegexStr);
	}
}
