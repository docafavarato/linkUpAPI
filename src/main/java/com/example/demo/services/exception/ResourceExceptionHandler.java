package com.example.demo.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandartError> badRequest(BadRequestException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Bad request", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
