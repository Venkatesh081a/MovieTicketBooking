package com.example.demo.exception;

public class InvalidTicketIdException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidTicketIdException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidTicketIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidTicketIdException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTicketIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidTicketIdException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
