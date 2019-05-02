package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import browserActions.NavbarInitializer;
import browserActions.PageLoader;
import dao.Order;
import utils.HTMLFiles;

/**
 * 
 * @author johannes
 *
 */
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
		NavbarInitializer.initNavbar(browser);
		fillInOrderAsText(order);
		initOrderTabButton();
	}
	
	private void fillInOrderAsText(Order order) {
		DOMDocument doc = browser.getDocument();
		// fill in the order name
		DOMInputElement orderNameInput = (DOMInputElement) doc.findElement(By.id("order-name"));
		orderNameInput.setValue(order.getOrdername());
		
		// create the template
		DOMElement p = doc.findElement(By.id("order-as-text"));
		String orderAsText = OrderPreviewTemplates.getGermanTemplate(order);
		p.setInnerText(orderAsText);
	}
	
	private void initOrderTabButton() {
		DOMDocument doc = browser.getDocument();
		DOMElement orderLink = doc.findElement(By.id("order-list-link"));
		orderLink.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
				PageLoader.loadGoogle(browser);
				OrderCreationView orderCreationView = new OrderCreationView(browser);
				orderCreationView.loadView();
			}
		}, false);
	}

}
