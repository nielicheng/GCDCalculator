package com.nie.test.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This test case suite test methods in GCDCalcator class.
 * @author lnie
 *
 */
public class GCDCalcatorTest {

	@Test(dataProvider = "dateProvider")
	public void testGCD(int num1, int num2, int expected) {
		int gcd = GCDCalcator.gcd(num1, num2);
		Assert.assertEquals(expected, gcd);
	}

	@DataProvider
	public Object[][] dateProvider() {
		return new Object[][] { 
				new Object[] { 20, 16, 4 } ,
				new Object[] { 20, 0, 20 },
				new Object[] { 0, 16, 16 },
				new Object[] { 1, 16, 1 }
				
			};
	}
}
