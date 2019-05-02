package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;


class TestBasicAuthentication {

	@Test
	void testUserName() {
		User anton = new User("Anton", "sftx2");
		String name = anton.getUsername();
		assertEquals("Anton", name);
	}
	
	@Test
	void testLogIn() {
		User anton = new User("Anton", "stfx2");
		BasicAuthentication aut = new BasicAuthentication();
		assertTrue(aut.login("Anton", "stfx2"));
	}

	@Test
	void testFailLogIn() {
		User anton = new User("Anton", "stfx2");
		BasicAuthentication aut = new BasicAuthentication();

		assertTrue(aut.login("Anton", "passwort"));
	}
}

