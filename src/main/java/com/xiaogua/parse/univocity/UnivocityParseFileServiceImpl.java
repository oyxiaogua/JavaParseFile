package com.xiaogua.parse.univocity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import com.xiaogua.parse.InterfaceParseFileService;
import com.xiaogua.parse.common.ProcessFileCommonUtil;

public class UnivocityParseFileServiceImpl implements InterfaceParseFileService {
	private Logger log = LoggerFactory.getLogger(UnivocityParseFileServiceImpl.class);

	public void parseFile(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		parseFileWithPartIndex(filePath, encoding, fileSep, destFilePath, destFileEncoding, destFileSep,
				batchSaveSize, hasHeader);
	}
	
	public void parseFileWithPartIndex(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		List<String[]> dataArrList = new ArrayList<String[]>(batchSaveSize);
		BufferedWriter bw = ProcessFileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding);
		CsvWriterSettings writerSettings = new CsvWriterSettings();
		writerSettings.getFormat().setDelimiter(destFileSep.charAt(0));
		CsvWriter writer = new CsvWriter(bw, writerSettings);

		Integer[] filedIndexArr=new Integer[]{1,3,6,10,15,21,28};
		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.setHeaderExtractionEnabled(false);
		parserSettings.setSkipEmptyLines(true);
		parserSettings.selectIndexes(filedIndexArr);
		parserSettings.getFormat().setDelimiter(fileSep.charAt(0));

		BufferedReader br = ProcessFileCommonUtil.getBufferedReader(filePath, encoding);
		CsvParser parser = new CsvParser(parserSettings);
		parser.beginParsing(br);
		String[] row;
		while ((row = parser.parseNext()) != null) {
			processRowDataWithPartField(row, dataArrList, writer, batchSaveSize);
		}
		processRemainData(dataArrList, writer);
		writer.close();
		dataArrList.clear();
		IOUtils.closeQuietly(bw);
		IOUtils.closeQuietly(br);
	}


	public void parseFileWithIteratorStyle(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		List<String[]> dataArrList = new ArrayList<String[]>(batchSaveSize);
		BufferedWriter bw = ProcessFileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding);
		CsvWriterSettings writerSettings = new CsvWriterSettings();
		writerSettings.getFormat().setDelimiter(destFileSep.charAt(0));
		CsvWriter writer = new CsvWriter(bw, writerSettings);

		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.setHeaderExtractionEnabled(false);
		parserSettings.setSkipEmptyLines(true);
		parserSettings.getFormat().setDelimiter(fileSep.charAt(0));

		BufferedReader br = ProcessFileCommonUtil.getBufferedReader(filePath, encoding);
		CsvParser parser = new CsvParser(parserSettings);
		parser.beginParsing(br);
		String[] row;
		while ((row = parser.parseNext()) != null) {
			processRowData(row, dataArrList, writer, batchSaveSize);
		}
		processRemainData(dataArrList, writer);
		writer.close();
		dataArrList.clear();
		IOUtils.closeQuietly(bw);
		IOUtils.closeQuietly(br);
	}

	public void parseFileWithRowProcessor(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		List<String[]> dataArrList = new ArrayList<String[]>(batchSaveSize);
		BufferedWriter bw = ProcessFileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding);
		CsvWriterSettings writerSettings = new CsvWriterSettings();
		writerSettings.getFormat().setDelimiter(destFileSep.charAt(0));
		CsvWriter writer = new CsvWriter(bw, writerSettings);

		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.setHeaderExtractionEnabled(false);
		parserSettings.setSkipEmptyLines(true);
		parserSettings.getFormat().setDelimiter(fileSep.charAt(0));
		parserSettings.setRowProcessor(new AbstractRowProcessor() {
			public void rowProcessed(String[] row, ParsingContext context) {
				processRowData(row, dataArrList, writer, batchSaveSize);
			}
		});

		BufferedReader br = ProcessFileCommonUtil.getBufferedReader(filePath, encoding);
		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(br);
		processRemainData(dataArrList, writer);
		writer.close();

		dataArrList.clear();
		IOUtils.closeQuietly(bw);
		IOUtils.closeQuietly(br);
	}

	private void processRemainData(List<String[]> dataArrList, CsvWriter writer) {
		if (dataArrList.size() > 0) {
			log.info("begin write remain row to file,size=" + dataArrList.size());
			for (String[] dataArr : dataArrList) {
				writer.writeRow(dataArr);
			}
		}
	}

	private void processRowData(String[] row, List<String[]> dataArrList, CsvWriter writer, int batchSaveSize) {
		boolean isValidate = ProcessFileCommonUtil.isValidateData(row, dataArrList);
		if (!isValidate) {
			return;
		}
		if (dataArrList.size() >= batchSaveSize) {
			log.info("begin write row to file,size=" + dataArrList.size());
			for (String[] dataArr : dataArrList) {
				writer.writeRow(dataArr);
			}
			writer.flush();
			dataArrList.clear();
		}
	}

	
	private void processRowDataWithPartField(String[] row, List<String[]> dataArrList, CsvWriter writer, int batchSaveSize) {
		boolean isValidate = ProcessFileCommonUtil.isValidateDataWithPartField(row, dataArrList);
		if (!isValidate) {
			return;
		}
		if (dataArrList.size() >= batchSaveSize) {
			log.info("begin write row to file,size=" + dataArrList.size());
			for (String[] dataArr : dataArrList) {
				writer.writeRow(dataArr);
			}
			writer.flush();
			dataArrList.clear();
		}
	}
}
