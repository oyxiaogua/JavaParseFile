package com.xiaogua.service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.service.InterfaceGenerateFileService;
import com.xiaogua.service.InterfaceRandomizer;

public class GenerateFileServiceImpl implements InterfaceGenerateFileService {
	private Logger log = LoggerFactory.getLogger(GenerateFileServiceImpl.class);

	private StringBuffer sb = new StringBuffer(256);
	// 随机企业编号
	private static final InterfaceRandomizer<String> entpRandomizer = new EntpCodeRandomizer();
	// 随机银行卡号
	private static final InterfaceRandomizer<String> luhnRandomizer = new LuhnRandomizer();

	public void generateFile(InterfaceRandomizer<String> nameRandomizer, InterfaceRandomizer<String> dateRandomizer,
			InterfaceRandomizer<String> phoneRandomizer, InterfaceRandomizer<String> emailRandomizer,
			InterfaceRandomizer<String> idRandomizer, InterfaceRandomizer<String> strRandomizer, String filePath,
			String encoding, final char separator, final int countNum) throws Exception {
		int printNumber = 1000;
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding))) {
			sb.setLength(0);
			sb.append("seqNum").append(separator);
			sb.append("name").append(separator);// 1
			sb.append("field_1").append(separator);
			sb.append("createDate").append(separator);// 3
			sb.append("field_2").append(separator);
			sb.append("field_3").append(separator);
			sb.append("phone").append(separator);// 6
			sb.append("field_4").append(separator);
			sb.append("field_5").append(separator);
			sb.append("field_6").append(separator);
			sb.append("email").append(separator);// 10
			sb.append("field_7").append(separator);
			sb.append("field_8").append(separator);
			sb.append("field_9").append(separator);
			sb.append("field_10").append(separator);
			sb.append("id").append(separator);// 15
			sb.append("field_11").append(separator);
			sb.append("field_12").append(separator);
			sb.append("field_13").append(separator);
			sb.append("field_14").append(separator);
			sb.append("field_15").append(separator);
			sb.append("entpCode").append(separator);// 21
			sb.append("field_16").append(separator);
			sb.append("field_17").append(separator);
			sb.append("field_18").append(separator);
			sb.append("field_19").append(separator);
			sb.append("field_20").append(separator);
			sb.append("field_21").append(separator);
			sb.append("luhnCode").append(separator);// 28
			sb.append("field_22").append(separator);
			sb.append("field_23").append(separator);
			sb.append("field_24").append(separator);
			sb.append("field_25").append(separator);
			sb.append("field_26").append(separator);
			sb.append("field_27").append(separator);
			sb.append("field_28");
			bw.write(sb.toString());
			bw.write("\r\n");
			for (int i = 1; i < countNum; i++) {
				sb.setLength(0);
				sb.append(i).append(separator);// 0
				sb.append(nameRandomizer.getRandomValue()).append(separator);// 1
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(dateRandomizer.getRandomValue()).append(separator);// 3
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(phoneRandomizer.getRandomValue()).append(separator);// 6
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(emailRandomizer.getRandomValue()).append(separator);// 10
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(idRandomizer.getRandomValue()).append(separator);// 15
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(entpRandomizer.getRandomValue()).append(separator);// 21
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(luhnRandomizer.getRandomValue()).append(separator);// 28
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue()).append(separator);
				sb.append(strRandomizer.getRandomValue());
				bw.write(sb.toString());
				bw.write("\r\n");
				if (i > 0 && i % printNumber == 0) {
					log.info("method {},generate number:{}", "generateFile", i);
				}
			}
		}

	}

}
