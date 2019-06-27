package browserActions;

import java.util.concurrent.Executors;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import browserViews.CustomerRegistrationView;

/**
 * 
 * @author johannes
 *
 */
public class NavbarInitializer {

    public static void initNavbar(Browser browser) {
    	DOMDocument doc = browser.getDocument();
    	DOMElement customerRegistrationNavbarButton = doc.findElement(By.id("customer-registration"));
    	customerRegistrationNavbarButton.addEventListener(DOMEventType.OnClick, domEvent -> {
			
			CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
			Executors.newCachedThreadPool().execute(customerRegistrationView::loadView);
				
			
		}, false);
    }

}
