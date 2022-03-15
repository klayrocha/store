package com.klayrocha.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StoreException extends RuntimeException {

	private static final long serialVersionUID = 8195470749118585215L;

	public StoreException(String exception) {
		super(exception);
	}

}
