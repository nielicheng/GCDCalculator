package com.nie.test.rs.sl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the implementation of Service Locator Pattern.<br/>
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Service_locator_pattern">Service
 *      Locator Pattern</a> </p>
 * @author lnie
 * 
 */
public class ServiceLocator {

	private static final Logger log = Logger.getLogger(ServiceLocator.class
			.getName());

	private Context initalContext;
	private Map<String, Object> cache;

	private static ServiceLocator instance = new ServiceLocator();

	public static ServiceLocator getInstance() {
		return instance;
	}

	private ServiceLocator() {

		try {
			this.initalContext = new InitialContext();

			this.cache = Collections
					.synchronizedMap(new HashMap<String, Object>());

		} catch (NamingException ex) {
			log.error("Failed to initialize context.", ex);
		}
	}

	/**
	 * Return a resource bound to a JNDI name provided.
	 * 
	 * @param servicJndiName
	 * @return
	 * @throws ServiceNotFoundException
	 *             Thrown if the resource can't be found.
	 */
	public Object getService(String servicJndiName)
			throws ServiceNotFoundException {

		Object object = null;
		try {

			if (this.cache.containsKey(servicJndiName)) {
				object = this.cache.get(servicJndiName);
			} else {
				Object service = initalContext.lookup(servicJndiName);
				if (service != null) {
					this.cache.put(servicJndiName, service);
					object = service;
				} else {
					throw new ServiceNotFoundException(
							"Failed to find service:" + servicJndiName);
				}
			}
			return object;
		} catch (Exception ex) {
			log.error("Failed to find service: " + servicJndiName, ex);
			throw new ServiceNotFoundException("Failed to find service:"
					+ servicJndiName, ex);
		}
	}

	/**
	 * Return resource of {@link UserTransaction}
	 * @return
	 * @throws ServiceNotFoundException Thrown if the resource can't be found.
	 */
	public UserTransaction getUserTransaction() throws ServiceNotFoundException {
		UserTransaction utx = (UserTransaction)getService("java:comp/UserTransaction");
		return utx;
	}
}
