package com.xiaogua.parse.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.parse.InterfaceParseFileService;
import com.xiaogua.parse.common.ProcessFileCommonUtil;

public class BufferedReaderWriterParseFileServiceImpl implements InterfaceParseFileService {
	private static final Logger log = LoggerFactory.getLogger(BufferedReaderWriterParseFileServiceImpl.class);
	private final StringBuffer sb = new StringBuffer(128);

	public void parseFile(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		final int batchQuerySize = 3000;
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(destFilePath), destFileEncoding));
		int start = 1;
		int end = start + batchQuerySize;
		List<String[]> dataArrList = new ArrayList<String[]>(batchSaveSize);
		List<String> dataList = null;
		File srcFile = new File(filePath);
		while (true) {
			log.info("start query batch file={},data range[{},{}]", filePath, start, end);
			dataList = ProcessFileCommonUtil.getRangeDataList(srcFile, start, end, hasHeader, encoding);
			if (dataList == null || dataList.size() == 0) {
				break;
			}
			processListData(dataList, fileSep, dataArrList, bw, destFileSep, batchSaveSize);
			start = end + 1;
			end = end + batchQuerySize;
			dataList.clear();
		}
		if (dataArrList.size() > 0) {
			writeDataToFile(dataArrList, bw, destFileSep);
		}
		dataArrList.clear();
		IOUtils.closeQuietly(bw);
	}

	/**
	 * 处理数据行
	 */
	private void processListData(List<String> dataList, String fileSep, List<String[]> dataArrList, BufferedWriter bw,
			String destFileSep, int batchSaveSize) throws Exception {
		Iterator<String> it = dataList.iterator();
		String dataStr = null;
		int ignoreNum = 0;
		int processLine = 0;
		boolean isValidate = false;
		while (it.hasNext()) {
			dataStr = it.next();
			isValidate = ProcessFileCommonUtil.isValidateData(dataStr, fileSep, dataArrList);
			if (!isValidate) {
				it.remove();
				ignoreNum++;
			}
			processLine++;
		}
		log.info("process line={},ignoreNum={}", processLine, ignoreNum);
		if (dataArrList.size() >= batchSaveSize) {
			log.info("start write to file,size={}", dataArrList.size());
			writeDataToFile(dataArrList, bw, destFileSep);
			dataArrList.clear();
		}
	}

	/**
	 * 数据写入文件
	 */
	private void writeDataToFile(List<String[]> dataArrList, BufferedWriter bw, String destFileSep) throws Exception {
		Iterator<String[]> it = dataArrList.iterator();
		String[] dataArr;
		while (it.hasNext()) {
			dataArr = it.next();
			sb.setLength(0);
			sb.append(dataArr[0]);
			for (int i = 1, len = dataArr.length; i < len; i++) {
				sb.append(destFileSep).append(dataArr[i]);
			}
			bw.write(sb.toString());
			bw.write("\r\n");
		}
		bw.flush();
	}

}
