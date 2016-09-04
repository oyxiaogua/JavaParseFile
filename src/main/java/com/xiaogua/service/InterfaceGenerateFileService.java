package com.xiaogua.service;

public interface InterfaceGenerateFileService {

	/**
	 * 生成随机文件
	 */
	public void generateFile(InterfaceRandomizer<String> nameRandomizer, InterfaceRandomizer<String> dateRandomizer,
			InterfaceRandomizer<String> phoneRandomizer, InterfaceRandomizer<String> emailRandomizer,
			InterfaceRandomizer<String> idRandomizer, InterfaceRandomizer<String> strRandomizer, String filePath,
			String encoding, final char separator, final int countNum) throws Exception;
}
