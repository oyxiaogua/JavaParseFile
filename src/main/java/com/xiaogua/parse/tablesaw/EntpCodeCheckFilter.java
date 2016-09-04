package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.EntpCodeCheck;

public class EntpCodeCheckFilter extends AbstractCheckFilter {

	public EntpCodeCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return EntpCodeCheck.isValidEntpCode(value);
	}

}
