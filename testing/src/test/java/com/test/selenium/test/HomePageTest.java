package com.test.selenium.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.test.selenium.base.SeleniumBase;

public class HomePageTest {

	SeleniumBase base;
	private String EXPECTED_TITLE_RISK_SOLUTIONS = "Risk Solutions";
	private String EXPECTED_TITLE_RISK_DECISION = "Risk Decision";
	
	@Before
	public void setup() {
		base = new SeleniumBase();
	}
	
	@Test
	public void test() {
		base.googleSearch("LexisNexis");
		String title = base.navigateGoogleLink(2);
		
		Assert.assertTrue(title.contains(EXPECTED_TITLE_RISK_SOLUTIONS));
		Assert.assertTrue(title.contains(EXPECTED_TITLE_RISK_DECISION));
	}

	@After
	public void cleanup() {
		base.cleanUpDriver();
	}
}
