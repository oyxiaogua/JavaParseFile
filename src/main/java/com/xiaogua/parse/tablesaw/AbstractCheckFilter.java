package com.xiaogua.parse.tablesaw;

import java.util.Iterator;

import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.Column;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.filtering.ColumnFilter;
import com.github.lwhite1.tablesaw.util.BitmapBackedSelection;
import com.github.lwhite1.tablesaw.util.Selection;

public abstract class AbstractCheckFilter extends ColumnFilter {

	public AbstractCheckFilter(ColumnReference columnReference) {
		super(columnReference);
	}

	public Selection apply(Table relation) {
		Column<?> column = relation.column(this.columnReference().getColumnName());
		CategoryColumn textColumn = (CategoryColumn) column;
		BitmapBackedSelection results = new BitmapBackedSelection();
		int i = 0;
		String value;
		for (Iterator<String> it = textColumn.iterator(); it.hasNext(); ++i) {
			value = (String) it.next();
			if (isValid(value)) {
				results.add(i);
			}
		}
		return results;
	}

	public abstract boolean isValid(String value);

}
