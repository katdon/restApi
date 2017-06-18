package pl.com.rest.LibraryApplication.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Set;


public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setCode(555);
        Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
        String message ="";

        for(ConstraintViolation<?> c : set) {
            String templ = "";
            templ += c.getMessage() + ": ";
            templ += c.getPropertyPath().toString();
            message += templ + " \n ";

        }
        errorMessage.setMessage(message);

        errorMessage.setDeveloperMessage(null);
        errorMessage.setLink(null);
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(ConstraintViolationException ex, ErrorMessage errorMessage) {

        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
