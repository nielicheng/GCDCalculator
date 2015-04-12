
package com.nie.test.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import com.nie.test.bean.NumberPair;
import com.nie.test.bean.QueueMngerLocal;
import com.nie.test.util.GCDCalcator;
import com.nie.test.ws.exceptipn.QueueEmptyException;
import com.nie.test.ws.exceptipn.ServerSystemException;

/**
 * This session bean implements GCD related logics and expose as SOAP based web service.
 * 
 * @author lnie
 */
@Stateless
@WebService(portName = "GCDPort", 
            serviceName = "GCDService", 
            targetNamespace = "http://com.nie.test/unicotest/ws", 
            endpointInterface = "com.nie.test.ws.GCDBeanService")
public class GCDBean implements GCDBeanService {

	private static final Logger log = Logger.getLogger(GCDBean.class.getName());
	
	@EJB
	private QueueMngerLocal queueMnger;

	/**
	 * {@inheritDoc}
	 * @throws QueueEmptyException Thrown when no data is in the queue.
	 */
	public int gcd() throws QueueEmptyException {
		try {
			NumberPair numPair = queueMnger.readTopElement();
			if(numPair == null) {
				throw new QueueEmptyException("Queue is empty.");
			}
			else {
				return GCDCalcator.gcd(numPair.getNumber1(), numPair.getNumber2());
			}
		} catch (JMSException e) {
			log.error("Failed to read from JMS queue", e);
			throw new ServerSystemException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws QueueEmptyException Thrown when no data is in the queue.
	 */
	public List<Integer> gcdList() throws QueueEmptyException {
		List<Integer> gcds = new ArrayList<Integer>();
		try {
			List<NumberPair> numPairs = queueMnger.readAll();
			if(numPairs.size() == 0) {
				throw new QueueEmptyException("Queue is empty.");
			}
			else {
				for (NumberPair numPair : numPairs) {
					int gcd = GCDCalcator.gcd(numPair.getNumber1(),
							numPair.getNumber2());
					gcds.add(gcd);
				}
				return gcds;
			}
		} catch (JMSException e) {
			log.error("Failed to read from JMS queue", e);
			throw new ServerSystemException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws QueueEmptyException Thrown when no data is in the queue. 
	 */
	public int gcdSum() throws QueueEmptyException {
		
		try {
			List<NumberPair> numPairs = queueMnger.readAll();
			if(numPairs.size() == 0) {
				throw new QueueEmptyException("Queue is empty.");
			}
			else {
				int sum = 0;
				for (NumberPair numPair : numPairs) {
					int gcd = GCDCalcator.gcd(numPair.getNumber1(),
							numPair.getNumber2());
					sum += gcd;
				}
				return sum;
			}
		} catch (JMSException e) {
			log.error("Failed to read from JMS queue", e);
			throw new ServerSystemException(e);
		}
	}
}
