/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nie.test.ws;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.nie.test.ws.exceptipn.QueueEmptyException;

/**
 * SEI of the web service.
 * @author lnie
 */
@Remote
@WebService(targetNamespace = "http://com.nie.test/unicotest/ws")
public interface GCDBeanService {
	/**
	 * Returns the greatest common divisor of the two integers at the head of the queue
	 * @return
	 * @throws QueueEmptyException 
	 */
	@WebMethod(operationName="gcd")
    public int gcd() throws QueueEmptyException;
    
    /**
     * Returns a list of all the computed greatest common divisors. 
     * @return
     * @throws QueueEmptyException 
     */
    public List<Integer> gcdList() throws QueueEmptyException;
    
    /**
     * Returns the sum of all computed greatest common divisors. 
     * @return
     * @throws QueueEmptyException 
     */
    public int gcdSum() throws QueueEmptyException;
}
