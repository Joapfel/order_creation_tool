package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;

public class MainTest {

	@Test
	public void testPriceCalculation() {
		BasicOrderComponent orderComp = new BasicOrderComponent();
		FixRate fixRate = new FixRate(orderComp);
		Material eisenstange = new Material(fixRate, "Eisenstangen", 5, 15);
		Machine lastenkran = new Machine(eisenstange, "Lastenkran", 2, 150);
		WorkingHours theOuter = new WorkingHours(lastenkran, "Junior", 8, 80);

		double price = theOuter.price();
		assertEquals(1015, price);

	}

	@Test
	public void testOrderStorage() {
		BasicOrderComponent orderComp = new BasicOrderComponent();
		FixRate fixRate = new FixRate(orderComp);
		Material eisenstange = new Material(fixRate, "Eisenstangen", 5, 15);
		Machine lastenkran = new Machine(eisenstange, "Lastenkran", 2, 150);
		WorkingHours theOuter = new WorkingHours(lastenkran, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", theOuter, "text");
		Serializer storageObject = new Serializer();

		storageObject.saveOrder(order);

		assertTrue(order.equals(storageObject.findOrderByID(order.getOrderID())));

	}
	
	@Test
	public void testOrderStorageWrongID() {
		BasicOrderComponent orderComp = new BasicOrderComponent();
		FixRate fixRate = new FixRate(orderComp);
		Material eisenstange = new Material(fixRate, "Eisenstangen", 5, 15);
		Machine lastenkran = new Machine(eisenstange, "Lastenkran", 2, 150);
		WorkingHours theOuter = new WorkingHours(lastenkran, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", theOuter, "text");
		Serializer storageObject = new Serializer();

		storageObject.saveOrder(order);
		
		Order order1 = storageObject.findOrderByID(0);
		//should fail
		assertTrue(order.equals(order1));
	}

	@Test
	public void testCustomerStorage() {
		BasicOrderComponent orderComp = new BasicOrderComponent();
		FixRate fixRate = new FixRate(orderComp);
		Material eisenstange = new Material(fixRate, "Eisenstangen", 3, 20);
		Machine lastenkran = new Machine(eisenstange, "Lastenkran", 1, 150);
		WorkingHours theOuter = new WorkingHours(lastenkran, "Junior", 4, 80);
		Order order = new Order("Herstellung von Eisenkunst", theOuter, "text");
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Customer kunde = new Customer("Kunstfirma", address, order);
		Serializer storageObject = new Serializer();

		storageObject.saveCustomer(kunde);

		assertTrue(kunde.equals(storageObject.findCustomersByCompanyName("Kunstfirma")));
	}

}
