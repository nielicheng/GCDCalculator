package com.nie.test.bean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.jboss.logging.Logger;

/**
 * This session bean encapsulates JMS queue related operations.  
 */
@Stateless
public class QueueMngerBean implements QueueMngerLocal {

	private static final Logger log = Logger.getLogger(QueueMngerBean.class
			.getName());

	/**
	 * Inject the queue that integer number is send to and read from.
	 */
	@Resource(mappedName = "queue/gcdQueue")
	private Queue gcdQueue;

	/**
	 * Inject {@link XAConnectionFactory} that supports distributed transaction. 
	 */
	@Resource(mappedName = "java:/JmsXA")
	private QueueConnectionFactory cf;

	private QueueConnection connection;

	@PostConstruct
	public void initQueueResources() throws JMSException {
		connection = cf.createQueueConnection();
		connection.start();
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendToQueue(int number1, int number2) throws JMSException {
		log.info("Send to queue: " + number1 + "," + number2);
		NumberPair content = new NumberPair();
		content.setNumber1(number1);
		content.setNumber2(number2);

		QueueSession queueSession = null;
		try {
			queueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			QueueSender sender = queueSession.createSender(gcdQueue);
			ObjectMessage message = queueSession.createObjectMessage();
			message.setObject(content);
			sender.send(message);
		} 
		finally {
			if (queueSession != null) {
				queueSession.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Integer> browseQueue() throws JMSException {
		
		QueueSession queueSession = null;

		List<Integer> results = new ArrayList<Integer>();
		try {
			queueSession = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			QueueBrowser queueBrowser = queueSession.createBrowser(gcdQueue);

			Enumeration e = queueBrowser.getEnumeration();

			while (e.hasMoreElements()) {
				ObjectMessage message = (ObjectMessage) e.nextElement();
				NumberPair pair = (NumberPair) message.getObject();
				results.add(pair.getNumber1());
				results.add(pair.getNumber2());
			}
			return results;

		} finally {
			if (queueSession != null) {
				queueSession.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public NumberPair readTopElement() throws JMSException {
		
		QueueSession queueSession = null;

		try {
			queueSession = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			QueueReceiver queueReceiver = queueSession.createReceiver(gcdQueue);

			ObjectMessage message = (ObjectMessage) queueReceiver
					.receiveNoWait();

			if(message != null && message.getObject() != null) {
				return (NumberPair) message.getObject();
			}
			else {
				return null;
			}

		} finally {
			if (queueSession != null) {
				queueSession.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<NumberPair> readAll() throws JMSException {
		
		QueueSession queueSession = null;

		List<NumberPair> results = new ArrayList<NumberPair>();
		try {
			queueSession = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			QueueReceiver queueReceiver = queueSession.createReceiver(gcdQueue);

			ObjectMessage message = (ObjectMessage) queueReceiver
					.receiveNoWait();
			while (message != null) {
				results.add((NumberPair) message.getObject());
				message = (ObjectMessage) queueReceiver.receiveNoWait();
			}

			return results;

		} finally {
			if (queueSession != null) {
				queueSession.close();
			}
		}
	}

	@PreDestroy
	public void releaseQueueResources() throws JMSException {
		if(connection != null) {
			connection.close();
		}
	}

}
