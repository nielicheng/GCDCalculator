package com.nie.test.rs.sl;

/**
 * <p>
 * This exception indicates that {@link ServiceLocator} can't find a service by the JNDI name provided.
 * </p>
 * @author lnie
 *
 */
public class ServiceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ServiceNotFoundException() {
		super();
	}

	public ServiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceNotFoundException(Throwable cause) {
		super(cause);
	}

	public ServiceNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString() {
		return errorMessage;
	}
}
