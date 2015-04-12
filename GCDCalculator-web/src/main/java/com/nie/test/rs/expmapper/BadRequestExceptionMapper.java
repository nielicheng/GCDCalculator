package com.nie.test.rs.expmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.BadRequestException;

/**
 * This class customize the return status and message when {@link BadRequestException} is thrown.
 * @author lnie
 *
 */
@Provider
public class BadRequestExceptionMapper  implements ExceptionMapper<BadRequestException>{

	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Data provided is wrong.").build();
	}

}
