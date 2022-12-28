package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.RegistrationResponse;
import com.example.demo.model.User;
import com.example.demo.service.ClientServiceImpl;


@RestController
@CrossOrigin("*")
public class ClientController {

	@Autowired
	ClientServiceImpl clientService;

	@PostMapping("/register")
	public RegistrationResponse addClient(@RequestBody RegistrationRequest req) {
		return clientService.addClient(req);
	}

	@PostMapping("/login")
	public User login(@RequestBody LoginRequest request) {
		return clientService.login(request);
	}
	
	@GetMapping("/userInfo/{userId}")
	public User getUserById(@PathVariable("userId") int userId) {
		return clientService.findUserById(userId);
		
	}

}
