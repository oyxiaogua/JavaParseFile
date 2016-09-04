package com.xiaogua.parse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.parse.univocity.UnivocityParseFileServiceImpl;

public class TestUnivocityParseFileService extends AbstractParentParse {
	private static final Logger log = LoggerFactory.getLogger(TestUnivocityParseFileService.class);

	@Test
	public void testParseFile() throws Exception {
		String destFilePath = "e:/test_tmp/0904_dest/univocityParseFileResult.txt";
		long startTime = System.currentTimeMillis();
		InterfaceParseFileService parseFileService = new UnivocityParseFileServiceImpl();
		parseFileService.parseFile(srcFilePath, srcFileEncoding, srcFileSeparator, destFilePath, destFileEncoding,
				destFileSeparator, batchSaveSize, hasHeader);
		log.error("UnivocityParseFileServiceImpl parse file cost {}", (System.currentTimeMillis() - startTime));
	}
}
