package org.lucasdc.exception;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.lucasdc.dto.ExceptionResponse;

@Provider
public class GeneralException implements ExceptionMapper<Exception> {
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {

        System.out.println("Error: " + e);

        if(e instanceof BusinessException) {
            var exceptionMessage = new ExceptionResponse(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionMessage).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro: entre em contado com o suporte").build();
    }
}
