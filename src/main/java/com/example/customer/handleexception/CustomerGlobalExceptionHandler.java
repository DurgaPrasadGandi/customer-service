package com.example.customer.handleexception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception ex, WebRequest request){
		ApiErrorResponse error=new ApiErrorResponse(
				new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false));
		return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({IllegalArgumentException.class, DataIntegrityViolationException.class})
	public ResponseEntity<ApiErrorResponse> BadRequest(Exception ex, WebRequest request){
		ApiErrorResponse error=new ApiErrorResponse(
				new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request Error",
                ex.getMessage(),
                request.getDescription(false));
		return new ResponseEntity(error,HttpStatus.BAD_REQUEST);  
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> customerNotFound(CustomerNotFoundException ex, WebRequest request){
		ApiErrorResponse error=new ApiErrorResponse(
				new Date(),
                HttpStatus.NOT_FOUND.value(),
                "Customer not found",
                ex.getMessage(),
                request.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	

}

