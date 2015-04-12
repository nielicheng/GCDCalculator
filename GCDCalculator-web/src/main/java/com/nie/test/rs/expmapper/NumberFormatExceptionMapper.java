package com.nie.test.rs.expmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class customize the return status and message when {@link NumberFormatException} is thrown.
 * @author lnie
 *
 */
@Provider
public class NumberFormatExceptionMapper implements ExceptionMapper<NumberFormatException>{

	@Override
	public Response toResponse(NumberFormatException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Data provied is wrong.").build();
	}

}
