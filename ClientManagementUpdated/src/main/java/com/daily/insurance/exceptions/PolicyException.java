package com.daily.insurance.exceptions;

@SuppressWarnings("serial")
public class PolicyException extends Exception {

	public PolicyException(String errorMessage) {
		super(errorMessage);
	}

}
