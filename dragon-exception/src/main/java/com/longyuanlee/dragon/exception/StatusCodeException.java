package com.longyuanlee.dragon.exception;

public class StatusCodeException extends DragonRuntimeException {

	private static final long serialVersionUID = 1L;

	private int statusCode;

	public StatusCodeException(int statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
