package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;

class TestCustomer {

	@Test
	void testAddress() {
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Address address2 = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		
		Order order = new Order("Muster", orderComp, "Bestellung",1);
		Customer kunde = new Customer("Musterfirma", address, order,1);
		
		assertTrue(address2.equals(kunde.getAddress()));
	}
	
	@Test
	void testCompanyName() {
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		String name = "Musterfirma";
		
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		
		Order order = new Order("Muster", orderComp, "Bestellung",1);
		Customer kunde = new Customer(name, address, order,1);
		
		assertTrue(name.equals(kunde.getCompanyName()));
	}

}
