package com.xiaogua.parse.tablesaw;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.QueryHelper;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.io.csv.CsvReader;
import com.xiaogua.check.DateCheck;
import com.xiaogua.parse.InterfaceParseFileService;

public class TableSawParseFileServiceImpl implements InterfaceParseFileService {
	private static final Logger log = LoggerFactory.getLogger(TableSawParseFileServiceImpl.class);
	private final StringBuffer sb = new StringBuffer(128);

	public void parseFile(String filePath, String encoding, String fileSep, String destFilePath,
			String destFileEncoding, String destFileSep, int batchSaveSize, boolean hasHeader) throws Exception {
		ColumnType[] columnTypeArr = new ColumnType[35];
		Arrays.fill(columnTypeArr, ColumnType.SKIP);
		int[] filedIndexArr = new int[] { 1, 3, 6, 10, 15, 21, 28 };
		for (int i : filedIndexArr) {
			columnTypeArr[i] = ColumnType.CATEGORY;
		}
		Table table = CsvReader.read(columnTypeArr, hasHeader, fileSep.charAt(0), filePath);
		long start=System.currentTimeMillis();
		Table rtnTable = table.selectWhere(QueryHelper.allOf(new ChineseNameCheckFilter(QueryHelper.column("name")),
				new DateCheckFilter(QueryHelper.column("createDate"), DateCheck.YYYY_MM_DD_HH_MM_SS),
				new PhoneNumCheckFilter(QueryHelper.column("phone")), new EmailCheckFilter(QueryHelper.column("email")),
				new IDCheckFilter(QueryHelper.column("id")), new EntpCodeCheckFilter(QueryHelper.column("entpCode")),
				new LuhnCheckFilter(QueryHelper.column("luhnCode"))));
		log.info("filter cost time={}",(System.currentTimeMillis()-start));
		//log.info("rtnTable rowCount={}", rtnTable.rowCount());
		CategoryColumn nameColumn = rtnTable.categoryColumn("name");
		CategoryColumn createDateColumn = rtnTable.categoryColumn("createDate");
		CategoryColumn phoneColumn = rtnTable.categoryColumn("phone");
		CategoryColumn emailColumn = rtnTable.categoryColumn("email");
		CategoryColumn idColumn = rtnTable.categoryColumn("id");
		CategoryColumn entpCodeColumn = rtnTable.categoryColumn("entpCode");
		CategoryColumn luhnCodeColumn = rtnTable.categoryColumn("luhnCode");
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(destFilePath), destFileEncoding));
		int totalNum = 0;
		for (int rowIndex : rtnTable) {
			sb.setLength(0);
			sb.append(nameColumn.getString(rowIndex)).append(destFileSep);
			sb.append(createDateColumn.getString(rowIndex)).append(destFileSep);
			sb.append(phoneColumn.getString(rowIndex)).append(destFileSep);
			sb.append(emailColumn.getString(rowIndex)).append(destFileSep);
			sb.append(idColumn.getString(rowIndex)).append(destFileSep);
			sb.append(entpCodeColumn.getString(rowIndex)).append(destFileSep);
			sb.append(luhnCodeColumn.getString(rowIndex));
			bw.write(sb.toString());
			bw.write("\r\n");
			totalNum++;
			if (totalNum % batchSaveSize == 0) {
				bw.flush();
				//log.info("flush to file ,totalNum={}", totalNum);
			}
		}
		IOUtils.closeQuietly(bw);
	}

}
