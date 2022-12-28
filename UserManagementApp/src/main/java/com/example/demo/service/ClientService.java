package com.example.demo.service;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.RegistrationResponse;
import com.example.demo.model.User;

public interface ClientService {

	public RegistrationResponse addClient(RegistrationRequest user);
	public User login(LoginRequest req);
	public User findUserById(int userId);
}
