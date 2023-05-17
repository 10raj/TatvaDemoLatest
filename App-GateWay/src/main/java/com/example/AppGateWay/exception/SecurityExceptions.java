package com.example.AppGateWay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SecurityExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SecurityExceptions(String exception) {
        super(exception);
    }
}
