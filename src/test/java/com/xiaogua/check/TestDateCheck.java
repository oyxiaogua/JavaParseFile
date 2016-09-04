package com.xiaogua.check;

import org.junit.Assert;
import org.junit.Test;

public class TestDateCheck {

	@Test
	public void testDateCheck() {
		String dateStr = "20150229";
		boolean rtnVal = DateCheck.isValidDate(dateStr);
		Assert.assertFalse(rtnVal);

		dateStr = "20160229";
		rtnVal = DateCheck.isValidDate(dateStr);
		Assert.assertTrue(rtnVal);

		dateStr = "2016229";
		rtnVal = DateCheck.isValidDate(dateStr);
		Assert.assertFalse(rtnVal);
	}
}
