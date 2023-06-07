package com.teste.santander.service.exceptions;

public class TransacoesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransacoesException(String msg) {
		super(msg);
	}

	public TransacoesException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
