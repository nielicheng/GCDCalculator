package com.nie.test.util;

/**
 * This class encapsulate greatest common divisor related algorithm.
 * @author lnie
 *
 */
public class GCDCalcator {

	/**
	 * Return greatest common divisor of two integer numbers.
	 * 
	 * @param num1 Integer number
	 * @param num2 Integer number
	 * @return Greatest common divisor of num1 and num2 passed in.
	 */
	public static int gcd(int num1, int num2) {
		if (num1 == 0 || num2 == 0)
			return num1 + num2;
		return gcd(num2, num1 % num2);
	}

}
