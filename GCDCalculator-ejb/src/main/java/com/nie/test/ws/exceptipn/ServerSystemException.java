package com.nie.test.ws.exceptipn;

import javax.xml.ws.ProtocolException;


/**
 * <p>
 * This exception indicates some system error happened in the server.
 * </p>
 * @author lnie
 *
 */
public class ServerSystemException extends ProtocolException {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ServerSystemException() {
		super();
	}

	public ServerSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerSystemException(Throwable cause) {
		super(cause);
	}

	public ServerSystemException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString() {
		return errorMessage;
	}
}
