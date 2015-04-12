package com.nie.test.bean.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity is mapped to Number_Pair table in database. <br/>
 * Number_Pair table stores all integer numbers ever added to queue.
 * @author lnie
 *
 */
@Entity
@Table(name="Number_Pair")
public class NumberPairEntity {
	@Id
    @GeneratedValue
    private Long id;
	
	private Integer number1;
	private Integer number2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumber1() {
		return number1;
	}
	public void setNumber1(Integer number1) {
		this.number1 = number1;
	}
	public Integer getNumber2() {
		return number2;
	}
	public void setNumber2(Integer number2) {
		this.number2 = number2;
	}
	
	
}
