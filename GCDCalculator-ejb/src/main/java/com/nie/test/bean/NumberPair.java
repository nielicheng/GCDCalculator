package com.nie.test.bean;

import java.io.Serializable;

/**
 * This class defines the structure of message stored in queue. 
 * @author lnie
 *
 */
public class NumberPair implements Serializable {
	private static final long serialVersionUID = 732511086094671404L;
	
	private int number1;
	private int number2;
	
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	
	
}
