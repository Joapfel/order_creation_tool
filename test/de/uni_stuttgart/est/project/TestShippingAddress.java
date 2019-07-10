package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.Address;
import dao.BasicOrderComponent;
import dao.FixRate;
import dao.Machine;
import dao.Material;
import dao.Order;
import dao.OrderComponent;
import dao.ShippingAddress;
import dao.WorkingHours;

/**
 * This class contains tests for the ShippingAddress class.
 * 
 * @author Anne
 *
 */
class TestShippingAddress {

	@Test
	void testShippingAddress() {
		OrderComponent orderComp = new BasicOrderComponent();

		Address shippingAddress = new Address("Ingeweg", 1, 75423, "Einöde", "Deutschland");
		ShippingAddress sAddress = new ShippingAddress(orderComp, shippingAddress);

		assertEquals("Ingeweg 1" + System.lineSeparator() + "75423 Einöde" + System.lineSeparator() + "Deutschland" + System.lineSeparator(), sAddress.addressToShipTo());
	}

}
