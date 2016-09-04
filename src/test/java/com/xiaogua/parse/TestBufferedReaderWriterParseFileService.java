package com.xiaogua.parse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.parse.basic.BufferedReaderWriterParseFileServiceImpl;
import com.xiaogua.service.TestDateStrRandomizer;

public class TestBufferedReaderWriterParseFileService extends AbstractParentParse {
	private static final Logger log = LoggerFactory.getLogger(TestDateStrRandomizer.class);

	@Test
	public void testParseFile() throws Exception {
		String destFilePath = "e:/test_tmp/0904_dest/bufferParseFileResult.txt";
		long startTime = System.currentTimeMillis();
		InterfaceParseFileService parseFileService = new BufferedReaderWriterParseFileServiceImpl();
		parseFileService.parseFile(srcFilePath, srcFileEncoding, srcFileSeparator, destFilePath, destFileEncoding,
				destFileSeparator, batchSaveSize, hasHeader);
		log.error("BufferedReaderWriterParseFileServiceImpl parse file cost {}",
				(System.currentTimeMillis() - startTime));

	}

}
