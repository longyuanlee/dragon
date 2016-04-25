package com.longyuanlee.dragon.exception;

public class DragonRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DragonRuntimeException(String message) {
		super(message);
	}

	public DragonRuntimeException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public DragonRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
