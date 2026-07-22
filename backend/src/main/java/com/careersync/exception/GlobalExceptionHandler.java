package com.careersync.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.careersync.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	   @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                HttpStatus.NOT_FOUND.value(),
	                ex.getMessage()
	        );

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
}
