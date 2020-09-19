package com.sda.ticketing.exceptions;

import com.sda.ticketing.exceptions.enums.ErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(value = {NullPointerException.class, SessionException.class,BookingException.class})
    protected ResponseEntity<Object> handleInternal(RuntimeException ex, WebRequest webRequest){
        ApiError apiError = ApiError.builder()
                .errorType(ErrorType.ERROR)
                .message("We are unable to complete your request, try again later")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(ex.getMessage())
                .developerMessage(ex.getMessage())
                .build();
        return this.handleExceptionInternal(ex,apiError,new HttpHeaders(),apiError.getStatus(),webRequest);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
