package com.xiaogua.parse.tablesaw;

import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.xiaogua.check.PhoneNumCheck;

public class PhoneNumCheckFilter extends AbstractCheckFilter {

	public PhoneNumCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public boolean isValid(String value) {
		return PhoneNumCheck.isValidPhoneNum(value);
	}

}
