package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.IDCheck;

public class IDCheckFilter extends AbstractCheckFilter {

	public IDCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return IDCheck.isValidID(value);
	}

}
