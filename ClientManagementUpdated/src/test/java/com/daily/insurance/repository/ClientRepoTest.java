package com.daily.insurance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.daily.insurance.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ClientRepoTest {

	@Autowired
	private ClientRepo clientRepo;

	private User user = new User();

	@BeforeEach
	public void init() {
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");
	}

	@Test
	void testFindClient() {
		User user1 = null;

		clientRepo.save(user);

		user1 = clientRepo.findById(user.getId()).get();
		assertEquals(user.getFirtName(), user1.getFirtName());
	}

}
