package com.xiaogua.service.impl;

import org.apache.commons.lang3.RandomStringUtils;

import com.xiaogua.check.EntpCodeCheck;
import com.xiaogua.service.InterfaceRandomizer;

/**
 * 组织机构代码生成
 * 
 * @author Test
 *
 */
public class EntpCodeRandomizer implements InterfaceRandomizer<String> {
	private static final char[] alphaNumericArr = EntpCodeCheck.entpCodeDataStr.toCharArray();
	private static final char separator = '-';
	private StringBuffer sb;

	public EntpCodeRandomizer() {
		super();
		this.sb = new StringBuffer(16);
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		String entpCode = RandomStringUtils.random(8, alphaNumericArr);
		this.sb.append(entpCode);
		this.sb.append(separator);
		this.sb.append(EntpCodeCheck.getEntpCodeCheckCode(entpCode));
		return this.sb.toString();
	}
}
