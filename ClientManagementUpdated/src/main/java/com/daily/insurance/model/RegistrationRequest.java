package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String emailId;
	private String password;
}
