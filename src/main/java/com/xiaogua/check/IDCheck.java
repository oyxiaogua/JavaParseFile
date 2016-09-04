package com.xiaogua.check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class IDCheck {
	private final static String[] idCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	private final static int[] idWeight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	public final static String provArr[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34",
			"35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64",
			"65", "71", "81", "82" };
	public final static List<String> provList = new ArrayList<String>(Arrays.asList(provArr));
	private static String numberRegexStr = "^[0-9]+$";

	public static boolean isValidID(String id) {
		if (StringUtils.isBlank(id)) {
			return false;
		}
		if (id.length() != 15 && id.length() != 18) {
			return false;
		}
		String idSubStr = null;
		if (id.length() == 18) {
			idSubStr = id.substring(0, 17);
		} else if (id.length() == 15) {
			idSubStr = id.substring(0, 6) + "19" + id.substring(6, 15);
		}
		if (!idSubStr.matches(numberRegexStr)) {
			return false;
		}
		String birthDateStr = idSubStr.substring(6, 14);
		boolean idValid = DateCheck.isValidDate(birthDateStr);
		if (!idValid) {
			return false;
		}
		idValid = provList.contains(idSubStr.substring(0, 2));
		if (!idValid) {
			return false;
		}
		if (id.length() == 18) {
			String checkCode = getIDCheckCode(idSubStr);
			if (!checkCode.equalsIgnoreCase(id.substring(17))) {
				return false;
			}
		}
		return true;
	}

	public static String getIDCheckCode(String code) {
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum = sum + Character.getNumericValue(code.charAt(i)) * idWeight[i];
		}
		int modValue = sum % 11;
		return idCodeArr[modValue];
	}
}
