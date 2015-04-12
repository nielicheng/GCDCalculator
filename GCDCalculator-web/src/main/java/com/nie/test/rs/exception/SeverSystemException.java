package com.nie.test.rs.exception;

/**
 * <p>
 * This exception indicates that some system error happens.
 * </p>
 * @author lnie
 *
 */
public class SeverSystemException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public SeverSystemException() {
		super();
	}

	public SeverSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeverSystemException(Throwable cause) {
		super(cause);
	}

	public SeverSystemException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString() {
		return errorMessage;
	}
}
