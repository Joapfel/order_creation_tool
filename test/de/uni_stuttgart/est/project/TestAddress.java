package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.Address;
import dao.BasicOrderComponent;
import dao.OrderComponent;

class TestAddress {

	@Test
	void testEquals() {
		Address address = new Address("Steinweg", 2, 2345, "Stadt", "Deutschland");
		Address address2 = new Address("Steinweg", 1, 1234, "Dorf", "Deutschland");
		Address address1 = new Address("Steinweg", 2, 2345, "Stadt", "Deutschland");
		OrderComponent orderComp = new BasicOrderComponent();
		
		assertFalse(address.equals(address2));
		assertFalse(address.equals(orderComp));
		assertTrue(address.equals(address1));
		assertTrue(address.equals(address));
	}
	
	@Test
	void testGet() {
		Address address = new Address("Kieselweg", 1, 1234, "Dorf", "Deutschland");
		assertEquals("Kieselweg", address.getStreetname());
		assertEquals(1, address.getHousenumber());
		assertEquals(1234, address.getZipcode());
		assertEquals("Dorf", address.getCity());
		assertEquals("Deutschland", address.getCountry());
	}

}
