package ratepay.bugtracker.service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ratepay.bugtracker.service.exception.BadRequestException;
import ratepay.bugtracker.service.exception.NoDataFoundException;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoData(NoDataFoundException exception) {
        return new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRequest(BadRequestException exception) {
        return new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage());
    }
}
