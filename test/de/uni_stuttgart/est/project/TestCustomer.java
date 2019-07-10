package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import dao.*;

/**
 * This class contains tests for the Customer class.
 * 
 * @author Anne
 *
 */
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

		Order order = new Order("Muster", orderComp, "Bestellung");
		Customer kunde = new Customer("Musterfirma", address, order);

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

		Order order = new Order("Muster", orderComp, "Bestellung");
		Customer kunde = new Customer(name, address, order);

		assertTrue(name.equals(kunde.getCompanyName()));
	}
	
	@Test
	void testAddOrder() {
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		String name = "Musterfirma";

		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		Order order = new Order("Muster", orderComp, "Bestellung");
		order.setOrderID(1);
		Customer kunde = new Customer(name, address, order);
		
		OrderComponent orderComp2 = new BasicOrderComponent();
		orderComp2 = new FixRate(orderComp2);
		orderComp2 = new Material(orderComp2, "Eisenstangen", 3, 20);
		orderComp2 = new Machine(orderComp2, "Lastenkran", 1, 150);
		orderComp2 = new WorkingHours(orderComp2, "Junior", 4, 80);
		Order order2 = new Order("Muster", orderComp2, "Bestellung");
		order2.setOrderID(2);
		kunde.addOrder(order2);
		
		LinkedList<Integer> orderID = new LinkedList<>();
		orderID.add(1);
		orderID.add(2);
		
		assertEquals(orderID, kunde.getOrder_id());
		
	}

}
