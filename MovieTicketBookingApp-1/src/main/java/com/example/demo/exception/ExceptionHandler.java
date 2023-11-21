package com.example.demo.exception;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidMoveIdException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionHandling invalidMoveIdExcepion(final InvalidMoveIdException exception,
			final HttpServletRequest request) {
		ExceptionHandling exceptionResult = new ExceptionHandling();
		exceptionResult.setMessage(exception.getMessage());
		exceptionResult.setUrl(request.getRequestURI());
		return exceptionResult;
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidTicketIdException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionHandling invalidTicketIdException(final InvalidTicketIdException exception,
			final HttpServletRequest request) {
		ExceptionHandling exceptionResult = new ExceptionHandling();
		exceptionResult.setMessage(exception.getMessage());
		exceptionResult.setUrl(request.getRequestURI());
		return exceptionResult;
	}
}
