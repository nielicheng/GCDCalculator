package com.nie.test.rs.expmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nie.test.rs.exception.SeverSystemException;

/**
 * This class customize the return status and message when {@link SeverSystemException} is thrown.
 * @author lnie
 *
 */
@Provider
public class SeverSystemExceptionMapper implements ExceptionMapper<SeverSystemException> {

	@Override
	public Response toResponse(SeverSystemException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Servier error, please contact administrator.").build();
	}

}
