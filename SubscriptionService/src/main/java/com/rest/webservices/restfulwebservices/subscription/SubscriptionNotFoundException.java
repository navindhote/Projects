package com.rest.webservices.restfulwebservices.subscription;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubscriptionNotFoundException extends RuntimeException {
	public SubscriptionNotFoundException(String message) {
		super(message);
	}
}