package com.cryptifyai.assessment.exception;

import com.cryptifyai.assessment.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class ResponseCustomErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomMovieException.class)
    public ResponseEntity<ErrorMessage> customMovieErrorHandler(CustomMovieException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
