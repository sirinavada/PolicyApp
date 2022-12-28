package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.RegistrationResponse;
import com.example.demo.model.User;
import com.example.demo.repository.ClientRepo;
import com.example.demo.service.ClientServiceImpl;


@AutoConfigureMockMvc
@SpringBootTest
class ClientServiceImplTest {

	@Mock
	private ClientRepo clientRepo;

	@InjectMocks
	private ClientServiceImpl clientService;

	@Test
	void testAddClient() {
		User user = new User();
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");

		RegistrationResponse res = new RegistrationResponse();
		res.setStatusCode("200");
		res.setResponseMessage("Registration successful");
		res.setUserName("raymond");

		RegistrationRequest req = new RegistrationRequest();
		req.setFirstName("Raymond");
		req.setLastName("Holt");
		req.setEmailId("raymond@gmail.com");
		req.setUserName("raymond");
		req.setPassword("Raymond@1");

		when(clientRepo.saveAndFlush(any())).thenReturn(user);
		assertEquals("raymond", user.getUserName());

		when(clientService.addClient(req)).thenReturn(res);
		assertEquals("200", res.getStatusCode());
	}

	@Test
	void testLogin() {
		User user = new User();
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");

		LoginRequest req = new LoginRequest();
		req.setUsername("raymond");
		req.setPassword("Raymond@1");

		when(clientRepo.save(any())).thenReturn(user);
		assertEquals("raymond", user.getUserName());

		when(clientService.login(req)).thenReturn(user);
	}

	@Test
	void testFindUserById() {
		User user = new User();
		user.setId(1);
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");

		when(clientRepo.findClient(any())).thenReturn(user);
		assertEquals("raymond", user.getUserName());

	}

}
