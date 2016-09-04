package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.DateCheck;

public class DateCheckFilter extends AbstractCheckFilter {
	private String dateFormatStr;

	public DateCheckFilter(ColumnReference columnReference, String dateFormatStr) {
		super(columnReference);
		this.dateFormatStr = dateFormatStr;
	}

	public DateCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return DateCheck.isValidDate(value, dateFormatStr);
	}

}
