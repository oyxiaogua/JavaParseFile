package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.ChineseNameCheck;

public class ChineseNameCheckFilter extends AbstractCheckFilter {

	public ChineseNameCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return ChineseNameCheck.isValidCnName(value);
	}

}
