package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.BasicOrderComponent;
import dao.FixRate;
import dao.Machine;
import dao.Material;
import dao.OrderComponent;
import dao.WorkingHours;

/**
 * This class contains tests for the WorkingHours class.
 * 
 * @author Anne
 *
 */
class TestWorkingHours {

	@Test
	void testPrice() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);

		assertEquals(1015, orderComp.price());
	}

	@Test
	void testSummary() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);

		String sep = System.lineSeparator();

		assertEquals("Order setzt sich zusammen aus: " + sep + sep
				+ "Eisenstangen: 5 Stück, 15.0 Euro/Stück, Gesamt: 75.0 Euro" + sep
				+ "Lastenkran: 2 Stunde(n), 150.0 Euro/Stunde, Gesamt: 300.0 Euro" + sep
				+ "Junior: 8 Stunde(n), 80.0 Euro/Stunde, Gesamt: 640.0 Euro", orderComp.summary());
	}

}
