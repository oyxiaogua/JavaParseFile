package com.xiaogua.parse;

public abstract class AbstractParentParse {
	String srcFilePath = "e:/test_tmp/0904/randomFile.txt";
	String srcFileEncoding = "UTF-8";
	String srcFileSeparator = ",";

	String destFileEncoding = "UTF-8";
	String destFileSeparator = "|";
	int batchSaveSize = 1000;
	boolean hasHeader = true;
}
