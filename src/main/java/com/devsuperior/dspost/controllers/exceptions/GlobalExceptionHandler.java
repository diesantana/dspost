package com.devsuperior.dspost.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dspost.models.dto.ErrorDetails;
import com.devsuperior.dspost.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErrorDetails error = new ErrorDetails();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());		
		
		return ResponseEntity.status(status).body(error);
	}
}
