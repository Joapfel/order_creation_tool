package browserViews;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.teamdev.jxbrowser.chromium.Browser;

import browserActions.*;
import dao.Address;
import dao.BasicOrderComponent;
import dao.Customer;
import dao.FixRate;
import dao.Order;
import dao.OrderComponentDecorator;
import dao.User;
import main.Initialize;
import storage.Serializer;
import storage.Storage;
import utils.*;

import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.DOMNode;

/**
 * 
 * @author marco
 *
 */
public class HRView implements View {

	private Browser browser;
	private final ExecutorService executorService;

	public HRView(Browser browser) {
		this.browser = browser;
		this.executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void loadView() {

		PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.HR_INTERFACE_VIEW.getHtmlFile());
		initAddButton(browser);
		initUserList(browser);
		deleteUserButton(browser);

	}

	private void initAddButton(Browser browser) {

		DOMDocument doc = browser.getDocument();
		DOMElement addButton = doc.findElement(By.id("button-addon-user"));
		
		addButton.addEventListener(DOMEventType.OnClick, domEvent -> {

			// get the entered user credentials
			DOMInputElement userIn = (DOMInputElement) doc.findElement(By.id("user-input"));
			DOMInputElement pwIn = (DOMInputElement) doc.findElement(By.id("password-input"));
			String username = userIn.getValue();
			String pw = pwIn.getValue();
			// save User
			Storage storage = Initialize.getSerializer();
			User saveUser = new User(username, pw);
			storage.saveUser(saveUser);
			// print User
			String html = "<li class=\"list-group-item item-used\">\n" + " <span class=\"user\">" + username
					+ " <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n"
					+ "</li>";
			String inner = doc.findElement(By.id("hr-user-list")).getInnerHTML();
			doc.findElement(By.id("hr-user-list")).setInnerHTML(inner + html);
			
			HRView hrView = new HRView(this.browser);
	    	this.executorService.execute(hrView::loadView);

		}, false);

	}

	private void initUserList(Browser browser) {

		DOMDocument doc = browser.getDocument();
		Serializer storage = Initialize.getSerializer();

		LinkedList<User> user_list = storage.getAllUsers();

		for (int i = 0; i < user_list.size(); i++) {

			User thisUser = user_list.get(i);

			String username = thisUser.getUsername();

			String html = "<li class=\"list-group-item item-used\">\n" + " <span class=\"user\">" + username + "</span>"
					+ "<button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n"
					+ "</li>";
			String inner = doc.findElement(By.id("hr-user-list")).getInnerHTML();
			doc.findElement(By.id("hr-user-list")).setInnerHTML(inner + html);
		}
	}

	private void deleteUserButton(Browser browser) {
		DOMDocument doc = browser.getDocument();
		Serializer storage = Initialize.getSerializer();
		List<DOMElement> deleteButtons = doc.findElements(By.xpath("//li/button[contains(@class, 'deleteButton')]"));
		for (DOMElement deleteButton : deleteButtons) {
			deleteButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {

				@Override
				public void handleEvent(DOMEvent arg0) {

					DOMNode li = deleteButton.getParent();
					DOMNode span = li.findElement(By.xpath("span"));
					String DelUser = span.getTextContent().trim();
					storage.deleteUserByUsername(DelUser);
					li.getParent().removeChild(li);
				}
			}, false);
		}
	}

}