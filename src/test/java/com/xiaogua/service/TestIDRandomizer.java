package com.xiaogua.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.service.impl.IDRandomizer;

public class TestIDRandomizer {
	private static final Logger log = LoggerFactory.getLogger(TestIDRandomizer.class);

	@Test
	public void testIDRandomizer() {
		IDRandomizer idRandomizer = new IDRandomizer("19900112","19950112",1);
		String str = idRandomizer.getRandomValue();
		log.info(str);
	}
}
