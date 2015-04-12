package com.nie.test.bean;

import java.util.List;

import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface QueueMngerLocal {
	/**
	 * Send two integer numbers to queue.
	 * @param number1 integer number
	 * @param number2 integer number
	 * @throws JMSException 
	 */
	public void sendToQueue(int number1, int number2) throws JMSException;
	
	/**
	 * View all integer numbers in the queue. All numbers will still be in queue after the operation.
	 *  
	 * @return A {@link List} of integer numbers. <br/>
	 * Return empty list if no number is in the queue.
	 * @throws JMSException
	 */
	public List<Integer> browseQueue() throws JMSException;
	
	/**
	 * Read the top element in the queue. The element will not exist in the queue after the operation.
	 * 
	 * @return The top element in the queue, {@link NumberPair} type.<br/>
	 * 		Return null if no element is in the queue. 
	 * @throws JMSException
	 */
	public NumberPair readTopElement() throws JMSException;
	
	/**
	 * Read all elements in the queue. These elements will not exist in the queue after the operation.
	 * 
	 * @return A {@link List} of {@link NumberPair} type elements. <br/> 
	 * Return empty list if no element is in the queue
	 * @throws JMSException
	 */
	public List<NumberPair> readAll() throws JMSException;
}
