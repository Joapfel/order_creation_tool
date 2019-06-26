package browserViews;

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
		initAddButton();

	}

	private void initAddButton() {

		DOMDocument doc = this.browser.getDocument();
		DOMElement addButton = doc.findElement(By.id("button-addon-user"));
		addButton.addEventListener(DOMEventType.OnClick, domEvent -> {

			// get the entered usercredentials
			DOMInputElement userIn = (DOMInputElement) doc.findElement(By.id("user-input"));
			DOMInputElement pwIn = (DOMInputElement) doc.findElement(By.id("password-input"));
			String username = userIn.getValue();
			String pw = pwIn.getValue();
			// save User
			Storage storage = Initialize.getSerializer();
			User saveUser = new User(username, pw);
			storage.saveUser(saveUser);

		}, false);
	}

	private void initUserList() {

		User user = new User(username);
		Serializer storage = Initialize.getSerializer();

		LinkedList<User> user_list = storage.getAllUsers();

		for (int i = 0; i < user_list.size(); i++) {
			User thisUser = user_list.get(i);

			String html = "<li class=\"list-group-item item-used\">\n" + " <span class=\"user\">" + user
					+ " <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n"
					+ "</li>";
			String inner = doc.findElement(By.id("hr-user-list")).getInnerHTML();
			doc.findElement(By.id("hr-user-list")).setInnerHTML(inner + html);
		}
	}

	private void deleteUserButton() {
		DOMDocument doc = browser.getDocument();
    	List<DOMElement> deleteButtons = doc.findElements(By.xpath("//li/button[contains(@class, 'deleteButton')]"));
    	for (DOMElement deleteButton : deleteButtons) {
    		deleteButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
				
				@Override
				public void handleEvent(DOMEvent arg0) {
					// TODO Auto-generated method stub
					DOMNode li = deleteButton.getParent();
					li.getParent().removeChild(li);
					
				}
			}, false);
    	}
	}

}