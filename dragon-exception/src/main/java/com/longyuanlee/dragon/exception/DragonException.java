package com.longyuanlee.dragon.exception;

public class DragonException extends Exception {

	private static final long serialVersionUID = 1L;

	public DragonException() {
		super();
	}

	public DragonException(String message) {
		super(message);
	}

	public DragonException(Throwable cause) {
		super(cause.getMessage(), cause);
	}
}
