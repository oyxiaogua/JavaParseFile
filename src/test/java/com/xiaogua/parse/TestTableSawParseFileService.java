package com.xiaogua.parse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.parse.tablesaw.TableSawParseFileServiceImpl;

public class TestTableSawParseFileService extends AbstractParentParse {
	private static final Logger log = LoggerFactory.getLogger(TestTableSawParseFileService.class);

	@Test
	public void testParseFile() throws Exception {
		String destFilePath = "e:/test_tmp/0904_dest/tableSawParseFileResult.txt";
		long startTime = System.currentTimeMillis();
		InterfaceParseFileService parseFileService = new TableSawParseFileServiceImpl();
		parseFileService.parseFile(srcFilePath, srcFileEncoding, srcFileSeparator, destFilePath, destFileEncoding,
				destFileSeparator, batchSaveSize, hasHeader);
		log.error("TableSawParseFileServiceImpl parse file cost {}", (System.currentTimeMillis() - startTime));
	}
}
