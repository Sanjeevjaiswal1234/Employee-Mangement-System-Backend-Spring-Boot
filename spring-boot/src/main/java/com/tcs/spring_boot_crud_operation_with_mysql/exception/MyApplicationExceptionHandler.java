package com.tcs.spring_boot_crud_operation_with_mysql.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class MyApplicationExceptionHandler {

	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<?> RuntimeException(RuntimeException exception) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		
		response.put("message = ", exception.getMessage());
		
		exception.printStackTrace();
		
		return ResponseEntity.ok(response);
		
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

		HashMap<String, String> response = new HashMap<String, String>();
		
		
//		List<FieldError> errors = exception.getFieldErrors();
//		
//		for (FieldError fieldError : errors) {
//			
//			String fieldName = fieldError.getField();
//			String message = fieldError.getDefaultMessage();
//
//			response.put(fieldName, message);
//		}
		
		exception.getFieldErrors().forEach(e->{
			String fieldName = e.getField();
            String message = e.getDefaultMessage();
            response.put(fieldName, message);
		});
		
		return ResponseEntity.ok(response);
	}
	 
	@ExceptionHandler(value = HandlerMethodValidationException.class)
	public ResponseEntity<?> handlerMethodValidationException(HandlerMethodValidationException exception) {

		HashMap<String, String> response = new HashMap<String, String>();

		exception.getAllErrors().forEach(e->{
			String fieldName = ((FieldError) e).getField();
			String message = e.getDefaultMessage();
			response.put(fieldName, message);
		});

		return ResponseEntity.ok(response);
		
	}

}
