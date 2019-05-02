package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;

import browserActions.PageLoader;
import dao.Order;
import utils.HTMLFiles;

public class OrderCreationViewPreview implements View{
	
	private Browser browser;
	private Order order;
	
	public OrderCreationViewPreview(Browser browser, Order order) {
		// TODO Auto-generated constructor stub
		this.browser = browser;
		this.order = order;
	}

	@Override
	public void loadView() {
		// TODO Auto-generated method stub
		PageLoader.loadHTMLFileComplete(browser, HTMLFiles.AUFTRAGSERSTELLUNG_VORSCHAU.getHtmlFile());
		fillInOrderAsText(order.getOrderAsText());
	}
	
	private void fillInOrderAsText(String orderAsText) {
		DOMDocument doc = browser.getDocument();
		DOMElement p = doc.findElement(By.id("order-as-text"));
		p.setInnerText(orderAsText);
	}

}
