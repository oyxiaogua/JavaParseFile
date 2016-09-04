package com.xiaogua.check;

import org.junit.Assert;
import org.junit.Test;

public class TestEmailCheck {

	@Test
	public void testEmailCheck() {
		String email = "ASE@qq.com";
		boolean isValid = EmailCheck.isValidEmail(email);
		Assert.assertTrue(isValid);

		email = "ASE+@qq.com";
		isValid = EmailCheck.isValidEmail(email);
		Assert.assertFalse(isValid);
		
		email = "ASE@a@qq.com";
		isValid = EmailCheck.isValidEmail(email);
		Assert.assertFalse(isValid);
		
		email = "ASE@.qq.com";
		isValid = EmailCheck.isValidEmail(email);
		Assert.assertFalse(isValid);
	}
}
