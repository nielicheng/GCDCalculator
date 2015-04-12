package com.nie.test.rs;

import java.util.List;

import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nie.test.bean.NumberMngerLocal;
import com.nie.test.bean.QueueMngerLocal;
import com.nie.test.rs.exception.SeverSystemException;
import com.nie.test.rs.sl.ServiceLocator;
import com.nie.test.rs.sl.ServiceNotFoundException;

/**
 * RESTful service implementation.
 * @author lnie
 *
 */
@Path("/gcdService")
public class GCDRestService {

	private static String QUEUE_MNG_BEAN_JNDI = "GCDCalculator-ear/QueueMngerBean/local";
	
	private static String NUM_PERSISTENT_BEAN_JNDI = "GCDCalculator-ear/NumberMngerBean/local";

	/**
	 * <p>
	 * Accept two integer parameters, then call {@link QueueMngerBean} to add them into queue, and call
	 * {@link NumberMngerBean} to store these two numbers into database.<br/>
	 * </p>
	 * <p>
	 * JTA is used to include the message sending and persistence in one transaction, 
	 * this will make sure they both succeed, or neither succeed.  
	 * </p>
	 * <p>
	 * Return "succeed" if the operation is successful. 
	 * </p>
	 * <p>
	 * If wrong parameters are passed, "400 Bad Request" and a user-friendly message will be returned, 
	 * this is done by {@link BadRequestExceptionMapper} and {@link NumberFormatExceptionMapper}.<br/>
	 * 
	 * If {@link SeverSystemException} is thrown, "500 Internal Server Error" 
	 * and a user-friendly message will be returned. This is done by {@link SeverSystemExceptionMapper}
	 * </p>
	 * @param i1 Integer type parameter
	 * @param i2 Integer type parameter
	 * 
	 * @return {@link String} type of status of the operation.
	 * 
	 * @throws SeverSystemException This exception is re-throw when system exception, 
	 * 		like JMSException, is caught. 
	 */
	@POST
	@Path("/create")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String push(@FormParam("i1") int i1, @FormParam("i2") int i2) throws SeverSystemException {
		
		UserTransaction utx = null;
		try{
			utx = ServiceLocator.getInstance().getUserTransaction();
			NumberMngerLocal numMngBean = getNumberMngerBean();
			QueueMngerLocal queueMngBean = getQueueMngerBean();
			
			utx.begin();
			
			queueMngBean.sendToQueue(i1, i2);
			
			numMngBean.storeNumberPair(i1,i2);

			utx.commit();
			
			return "succeed";
		}
		catch(Exception e) {
			try {
				utx.rollback();
			} catch (Exception e1) {
				throw new SeverSystemException(e1);
			}
			throw new SeverSystemException(e);
		}
	}

	/**
	 * <p>
	 * Returns a list of all the elements ever added to the queue.<br/>
	 * These elements are retrieved from database.
	 * </p>
	 * @return A list of elements ever added to the queue in JSON format.
	 * @throws SeverSystemException This exception is re-throw when system exception, 
	 * 		like JMSException, is caught.
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> list() throws SeverSystemException{
		try {
		
			NumberMngerLocal numMng = getNumberMngerBean();
			List<Integer> ints = numMng.findAll();
			return ints;
		}
		catch(Exception e) {
			throw new SeverSystemException(e);
		}
	}
	
	/**
	 * This is only for my test purpose to retrieve all elements currently in the queue.
	 * @return A list of elements currently in the queue in JSON format.
	 * @throws SeverSystemException
	 */
	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> view() throws SeverSystemException{
		try {
			QueueMngerLocal queueMng = getQueueMngerBean();
			List<Integer> ints = queueMng.browseQueue();
			return ints;
		}
		catch(Exception e) {
			throw new SeverSystemException(e);
		}
	}

	/**
	 * This is helper method to retrieve bean that implements {@link QueueMngerLocal} 
	 * @return
	 * @throws ServiceNotFoundException Thrown when {@link QueueMngerLocal} type resource can't be found. 
	 */
	private QueueMngerLocal getQueueMngerBean() throws ServiceNotFoundException {
		QueueMngerLocal queueMng = (QueueMngerLocal) ServiceLocator
				.getInstance().getService(QUEUE_MNG_BEAN_JNDI);
		return queueMng;
	}
	
	/**
	 * This is helper method to retrieve bean that implements {@link NumberMngerLocal}
	 * @return
	 * @throws ServiceNotFoundException Thrown when {@link NumberMngerLocal} type resource can't be found. 
	 */
	private NumberMngerLocal getNumberMngerBean() throws ServiceNotFoundException {
		NumberMngerLocal numMng = (NumberMngerLocal) ServiceLocator
				.getInstance().getService(NUM_PERSISTENT_BEAN_JNDI);
		return numMng;
	}
}
