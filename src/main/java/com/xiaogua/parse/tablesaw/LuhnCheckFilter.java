package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.LuhnCheck;

public class LuhnCheckFilter extends AbstractCheckFilter {

	public LuhnCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return LuhnCheck.isValidLuhn(value);
	}

}
