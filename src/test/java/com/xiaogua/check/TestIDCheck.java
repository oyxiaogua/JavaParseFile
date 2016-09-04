package com.xiaogua.check;

import org.junit.Assert;
import org.junit.Test;

public class TestIDCheck {

	@Test
	public void testIDCheck() {
		String str = "371601198103165968";
		boolean isValid = IDCheck.isValidID(str);
		Assert.assertTrue(isValid);

		str = "371601810316596";
		isValid = IDCheck.isValidID(str);
		Assert.assertTrue(isValid);

		str = "371601802316596";
		isValid = IDCheck.isValidID(str);
		Assert.assertFalse(isValid);

		str = "32130019841120439x";
		isValid = IDCheck.isValidID(str);
		Assert.assertTrue(isValid);
	}
}
