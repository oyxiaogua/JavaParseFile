package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.EmailCheck;

public class EmailCheckFilter extends AbstractCheckFilter {

	public EmailCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return EmailCheck.isValidEmail(value);
	}

}
