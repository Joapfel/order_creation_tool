package browserViews;

import dao.Order;

public class OrderPreviewTemplates {

	public static String getGermanTemplate(Order order) {
		String sep = System.lineSeparator();
		return "Sehr geehrter Kunde," + sep
				+ "vielen Dank für Ihre Anfrage." + sep
				+ sep
				+ "Sehr gerne würden wir mit Ihnen zusammenarbeiten." + sep
				+ "Aus dem herausgearbeiteten Angebot enstehen folgende Kosten:" + sep
				+ order.getOrderAsText() + sep
				+ sep
				+ "Summe des Gesamtangebots: " + order.getOrderComponent().price() + " Euro" + sep
				+ sep
				+ "Wir freuen uns auf Ihre Rückmeldung." + sep
				+ "Mit freundlichen Grüßen" + sep
				+ "Ihre Metallschreinerei";
	}
}
