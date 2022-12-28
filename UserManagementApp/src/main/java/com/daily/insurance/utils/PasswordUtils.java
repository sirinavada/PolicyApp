package com.daily.insurance.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

	private static final Logger logger = LogManager.getLogger(PasswordUtils.class);

	public String hashPassword(String plainTextPassword) {
		String encPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		logger.info("Enc password: {}", encPassword);
		return encPassword;
	}

	public boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			logger.info("The password matches.");
			return true;
		} else {
			logger.info("The password does not match.");
			return false;
		}
	}

}
