package com.xiaogua.parse.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.check.ChineseNameCheck;
import com.xiaogua.check.DateCheck;
import com.xiaogua.check.EmailCheck;
import com.xiaogua.check.EntpCodeCheck;
import com.xiaogua.check.IDCheck;
import com.xiaogua.check.LuhnCheck;
import com.xiaogua.check.PhoneNumCheck;

public class ProcessFileCommonUtil {
	private static final Logger log = LoggerFactory.getLogger(ProcessFileCommonUtil.class);

	/**
	 * 按范围获取数据行
	 */
	public static List<String> getRangeDataList(File file, int includeLowerBound, int includeUpperBound,
			boolean skipFirstLine, String encoding) throws Exception {
		List<String> dataLineList = new ArrayList<String>(includeUpperBound - includeLowerBound + 1);
		BufferedInputStream bis = null;
		BufferedReader br = null;
		int i = 0;
		String tmpStr = null;
		boolean isFirst = true;
		boolean isAdd = false;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			br = new BufferedReader(new InputStreamReader(bis, encoding));
			while ((tmpStr = br.readLine()) != null) {
				if (tmpStr.trim().length() == 0) {
					continue;
				}
				if (isFirst) {
					isFirst = false;
					if (skipFirstLine) {
						continue;
					}
					tmpStr = tmpStr.replace("\uFEFF", "");
				}
				i++;
				if (i < includeLowerBound) {
					continue;
				}
				if (i > includeUpperBound) {
					break;
				}
				dataLineList.add(tmpStr);
				isAdd = true;
			}
			if (!isAdd) {
				return new ArrayList<String>();
			} else {
				return dataLineList;
			}
		} catch (Exception e) {
			log.error(" read content error:", e);
		} finally {
			closeBufferedStream(br, bis);
		}
		return new ArrayList<String>();
	}

	/**
	 * 关闭流
	 */
	public static void closeBufferedStream(BufferedReader br, BufferedInputStream bis) {
		try {
			IOUtils.closeQuietly(br);
		} catch (Exception e) {
			log.error(" close  bufferedreader error:", e);
		}
		try {
			IOUtils.closeQuietly(bis);
		} catch (Exception e) {
			log.error("close bufferedinputstream error:", e);
		}
	}

	public static BufferedReader getBufferedReader(String filePath, String encoding) throws Exception {
		return new BufferedReader(
				new InputStreamReader(new BufferedInputStream(new FileInputStream(filePath)), encoding));
	}

	public static BufferedWriter getBufferedWriter(String filePath, String encoding) throws Exception {
		return getBufferedWriter(filePath, encoding, false);
	}

	public static BufferedWriter getBufferedWriter(String filePath, String encoding, boolean append) throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath, append), encoding);
		BufferedWriter bw = new BufferedWriter(osw);
		return bw;
	}

	/**
	 * 是否合法数据
	 */
	public static boolean isValidateData(String str, String fileSep, List<String[]> dataArrList) {
		String[] dataArr = str.split(fileSep, -1);
		return isValidateData(dataArr, dataArrList);
	}

	public static boolean isValidateData(String[] dataArr, List<String[]> dataArrList) {
		if (dataArr.length < 29) {
			return false;
		}
		// 姓名
		boolean isValid = ChineseNameCheck.isValidCnName(dataArr[1]);
		if (!isValid) {
			return false;
		}
		// 时间
		isValid = DateCheck.isValidDate(dataArr[3], DateCheck.YYYY_MM_DD_HH_MM_SS);
		if (!isValid) {
			return false;
		}
		// 号码
		isValid = PhoneNumCheck.isValidPhoneNum(dataArr[6]);
		if (!isValid) {
			return false;
		}
		// email
		isValid = EmailCheck.isValidEmail(dataArr[10]);
		if (!isValid) {
			return false;
		}
		// id
		isValid = IDCheck.isValidID(dataArr[15]);
		if (!isValid) {
			return false;
		}
		isValid = EntpCodeCheck.isValidEntpCode(dataArr[21]);
		if (!isValid) {
			return false;
		}
		isValid = LuhnCheck.isValidLuhn(dataArr[28]);
		if (!isValid) {
			return false;
		}
		String[] rtnDataArr = new String[7];
		rtnDataArr[0] = dataArr[1];
		rtnDataArr[1] = dataArr[3];
		rtnDataArr[2] = dataArr[6];
		rtnDataArr[3] = dataArr[10];
		rtnDataArr[4] = dataArr[15];
		rtnDataArr[5] = dataArr[21];
		rtnDataArr[6] = dataArr[28];
		dataArrList.add(rtnDataArr);
		return true;
	}

	public static boolean isValidateDataWithPartField(String[] dataArr, List<String[]> dataArrList) {
		if (dataArr.length < 7) {
			return false;
		}
		// 姓名
		boolean isValid = ChineseNameCheck.isValidCnName(dataArr[0]);
		if (!isValid) {
			return false;
		}
		// 时间
		isValid = DateCheck.isValidDate(dataArr[1], DateCheck.YYYY_MM_DD_HH_MM_SS);
		if (!isValid) {
			return false;
		}
		// 号码
		isValid = PhoneNumCheck.isValidPhoneNum(dataArr[2]);
		if (!isValid) {
			return false;
		}
		// email
		isValid = EmailCheck.isValidEmail(dataArr[3]);
		if (!isValid) {
			return false;
		}
		// id
		isValid = IDCheck.isValidID(dataArr[4]);
		if (!isValid) {
			return false;
		}
		isValid = EntpCodeCheck.isValidEntpCode(dataArr[5]);
		if (!isValid) {
			return false;
		}
		isValid = LuhnCheck.isValidLuhn(dataArr[6]);
		if (!isValid) {
			return false;
		}
		dataArrList.add(dataArr);
		return true;
	}

}
