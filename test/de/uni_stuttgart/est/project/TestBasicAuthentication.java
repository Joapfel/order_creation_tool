package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import authentication.BasicAuthentication;
import dao.*;
import main.Initialize;

/**
 * This class contains tests for the BasicAuthentication class.
 * 
 * @author Anne
 *
 */
class TestBasicAuthentication {

	@Test
	void testLogIn() {
		Initialize.getSerializer();
		//User anton = new User("Testuser", "est");
		BasicAuthentication aut = new BasicAuthentication();
		assertTrue(aut.login("Testuser", "est"));
	}

	@Test
	void testFailLogIn() {
		Initialize.getSerializer();
		//User anton = new User("Testuser", "est");
		BasicAuthentication aut = new BasicAuthentication();
		assertFalse(aut.login("Testuser", "passwort"));
	}
	
	@Test
	void testNotExistingUser() {
		Initialize.getSerializer();
		BasicAuthentication aut = new BasicAuthentication();
		assertFalse(aut.login("Bert", "qwert"));
	}
}
