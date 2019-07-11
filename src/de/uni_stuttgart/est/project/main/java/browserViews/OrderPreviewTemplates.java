package browserViews;

import dao.Order;

/**
 * 
 * @author johannes
 *
 */
public class OrderPreviewTemplates {

	public static String getGermanTemplate(Order order) {
		String sep = System.lineSeparator();
		return  order.getOrdername()
				+ sep
				+ "-------------------------------------"
				+ sep
				+ sep
				+ "Sehr geehrter Kunde," + sep
				+ "vielen Dank für Ihre Anfrage." + sep
				+ sep
				+ "Sehr gerne wuerden wir mit Ihnen zusammenarbeiten." + sep
				+ "Im folgenden unser Angebot an Sie:" + sep
				+ order.getOrderAsText() + sep
				+ sep
				+ "Summe des Gesamtangebots: " + order.getOrderComponent().price() + " Euro" + sep
				+ sep
				+ "Wir freuen uns auf Ihre Rueckmeldung." + sep
				+ "Mit freundlichen Grueßen" + sep
				+ "Ihre Metallschreinerei";
	}
}
