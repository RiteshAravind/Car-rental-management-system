package com.masai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ErrorDetails> userNotFoundException(EntityNotFound entityNotFound, WebRequest webRequest){

        ErrorDetails ed = new ErrorDetails(LocalDateTime.now(),entityNotFound.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerFoundException(NoHandlerFoundException ex, WebRequest webRequest){

        ErrorDetails myErrorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));


        return new ResponseEntity<ErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exception(Exception ex, WebRequest webRequest){

        ErrorDetails myErrorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));


        return new ResponseEntity<ErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
    }


}
