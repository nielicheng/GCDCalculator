package com.nie.test.bean;

import java.util.List;

import javax.ejb.Local;

@Local
public interface NumberMngerLocal {
	
	/**
	 * Store two integer type parameters into database.
	 * 
	 * @param number1 integer parameter
	 * @param number2 integer parameter
	 */
	public void storeNumberPair(int number1, int number2);
	
	/**
	 * Retrieve all elements from database.
	 * @return A {@link List} of integers retrieved from database. 
	 * 		Return an empty list if no data is in database.
	 */
	public List<Integer> findAll();
}
