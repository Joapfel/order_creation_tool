package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;
import main.Main;

class MainTest {
	
	@Test
	public void testPriceCalculation() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		double price = orderComp.price();
		assertEquals(1015, price);

	}

	@Test
	public void testOrderStorage() {
		Serializer storageObject = Main.initialize();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text", 1);
		

		storageObject.saveOrder(order);

		assertTrue(order.equals(storageObject.findOrderByID(order.getOrderID())));

	}

	@Test
	public void testOrderStorageWrongID() {
		Serializer storageObject = new Serializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text", 1);

		storageObject.saveOrder(order);

		Order order1 = storageObject.findOrderByID(0);
		// should fail
		assertFalse(order.equals(order1));
	}

	@Test
	public void testCustomerStorage() {
		Serializer storageObject = new Serializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text", 1);
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Customer kunde = new Customer("Kunstfirma", address, order, 1);
		

		storageObject.saveCustomer(kunde);

		assertTrue(kunde.equals(storageObject.findCustomersByCompanyName("Kunstfirma")));
	}
}
