package com.nie.test.ws.exceptipn;

import javax.xml.ws.WebFault;

/**
 * <p>
 * This exception indicates that the queue is empty.<br/>
 * Once thrown, this exception is included in SOAP message and passed to web service client.
 * </p>
 * @author lnie
 *
 */
@WebFault(name="QueueEmptyFault",targetNamespace="http://com.nie.test/unicotest/ws/type")
public class QueueEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;
	
	private FaultInfo faultInfo = new FaultInfo();

	public QueueEmptyException() {
		super();
	}

	public QueueEmptyException(String message, Throwable cause) {
		super(message, cause);
		faultInfo.setMessage(message);
	}

	public QueueEmptyException(Throwable cause) {
		super(cause);
	}

	public QueueEmptyException(String errorMessage) {
		this.message = errorMessage;
		faultInfo.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		return message;
	}

	public FaultInfo getFaultInfo() {
		return faultInfo;
	}

}
