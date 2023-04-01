package com.store.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

  @Override
  public Response toResponse(DataNotFoundException exception) {
    ErrorMessage errorMessage = new ErrorMessage(404, exception.getMessage());
    return Response.status(Status.NOT_FOUND)
        .type(MediaType.APPLICATION_JSON)
        .entity(errorMessage)
        .build();
  }

}
