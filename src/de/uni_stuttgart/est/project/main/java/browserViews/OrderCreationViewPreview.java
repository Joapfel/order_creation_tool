package browserViews;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
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
	private final ExecutorService executorService;
	
	public OrderCreationViewPreview(Browser browser, Order order) {
		// TODO Auto-generated constructor stub
		this.browser = browser;
		this.order = order;
		this.executorService = Executors.newCachedThreadPool();
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
		orderLink.addEventListener(DOMEventType.OnClick, domEvent -> {
			// TODO Auto-generated method stub
			OrderCreationView orderCreationView = new OrderCreationView(browser, order);
			this.executorService.execute(orderCreationView::loadView);
			
		}, false);
	}

}
