package com.xiaogua.parse;

public interface InterfaceParseFileService {

	/**
	 * 解析文件到文件
	 * 
	 * @param filePath:原文件路径
	 * @param encoding:原文件编码
	 * @param fileSep:原文件分隔符
	 * @param destFilePath:目标文件路径
	 * @param destFileEncoding:目标文件编码
	 * @param destFileSep:目标文件分隔符
	 */
	public void parseFile(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep,int batchSaveSize,boolean hasHeader) throws Exception;

}
