package com.example.demo.model;

public enum Constants {	
	
	SUCCESS_CODE("200"), 
	REG_SUCCESS_MSG("Registration successful"),
	CLAIM_ELIGIBLE("Eligible"),
	CLAIM_COMPLETE("Complete"),
	DATE_FORMAT("yyyy-MM-dd HH:mm:ss");

	Constants(String s) {
		System.out.println(s);
	}
}
