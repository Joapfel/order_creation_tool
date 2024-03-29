package browserViews;

import java.util.ArrayList;
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
import main.Initialize;
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
 * @author johannes
 *
 */
public class CustomerRegistrationView implements View{

    private Browser browser;
    private final ExecutorService executorService;
    private Storage storage;

    public CustomerRegistrationView(Browser browser){
        this.browser = browser;
        this.executorService = Executors.newCachedThreadPool();
        this.storage = Initialize.getSerializer();
    }

    @Override
    public void loadView() {

        PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.KUNDENERFASSUNG.getHtmlFile());
        loadExistingCustomers();
        initNewOrderButtons();
        initNewCustomerButton();
        initEditOrderButtons();
       
    }
    
    private void addCustomerHTML(String companyName) {
    	
    	DOMDocument document = this.browser.getDocument();
    	Customer customer = storage.findCustomersByCompanyName(companyName);
   
    	ArrayList<Order> orders = new ArrayList<Order>();
    	List<Integer> orderIds = customer.getOrder_id();
    	if (orderIds != null && orderIds.size() > 0) {
        	for (int j : orderIds) {
        		Order order = storage.findOrderByID(j);
        		if (order != null) {
        			orders.add(order);
        		}	
        	}
    	}
    	
		String html =
                "<div id="+companyName+" class='card'>\n" +
                "  <div class='card-header' id='headingFour'>\n" +
                "    <h2 class='mb-0'>\n" +
                "      <button class='btn btn-link collapsed' type='button' data-toggle='collapse' data-target='#collapseFour' aria-expanded='false' aria-controls='collapseFour'>\n" +
                "        <span class='oi oi-caret-bottom'></span>\n" +
                "      </button>\n" +
                        companyName +
                "      <button class='btn btn-primary my-2 my-sm-0 float-right new-order' type='submit'>Neuer Auftrag</button>\n" +
                "    </h2>\n" +
                "  </div>\n" +
                //
                "</div>\n";
		
		
		DOMElement accordion = document.findElement(By.id("accordionExample"));
		String inner = accordion.getInnerHTML();
		document.findElement(By.id("accordionExample")).setInnerHTML(inner + html);
		
		if (orders != null && orders.size() > 0) {
			String ordersHTML = addOrderToCustomerHTML(orders);
			if (ordersHTML != null && !ordersHTML.equals("")) {
				DOMElement divCard = document.findElement(By.id(companyName));
				
				if (divCard != null) {
					String divCardInner = divCard.getInnerHTML();
					divCard.setInnerHTML(divCardInner + ordersHTML);
				}
			}
		}		
		
    }
    
    private String addOrderToCustomerHTML(List<Order> orders) {
    	
    	String openDivCard = "<div class=\"card\">";
    	String closedDivCard = "</div>";
    	String openUl = " <ul class=\"list-group list-group-flush\">";
    	String closedUl = "</ul>";
    	
    	StringBuilder ul = new StringBuilder();
    	ul.append(openUl);
    	
    	int added = 0;
    	for (Order order : orders) {
    		if (order == null) { continue; }
        	String li = " <li class=\"list-group-item\">\n" + 
        			"     	<span>" + order.getOrdername() + " <span class=\"orderId\"> (" + order.getOrderID() + ")</span> </span>\n" + 
        			"     	<div class=\"float-right\">\n" +  
        			"       	<button class=\"btn btn-outline-primary my-2 my-sm-0 float-right edit \" type=\"submit\">Bearbeiten</button>\n" + 
        			"       </div>\n" + 
        			"     </li>";
        	ul.append(li);
        	added += 1;
    	}
    	ul.append(closedUl);
    	
    	StringBuilder divCard = new StringBuilder();
    	divCard.append(openDivCard);
    	divCard.append(ul);
    	divCard.append(closedDivCard);
    	
    	if (added > 0) {
    		return divCard.toString();
    	}
    	return "";
    }
    
    
    private void loadExistingCustomers() {
    	
		LinkedList<Customer> cust_list = storage.getAllCustomers();
		
		for(int i = 0;i<cust_list.size();i++) {
			Customer cust = cust_list.get(i);
			addCustomerHTML(cust.getCompanyName());

		}
    }
    
    
    private void initNewCustomerButton() {
        // save the customer
        DOMElement saveButton = this.browser.getDocument().findElement(By.id("save-customer"));
        saveButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
            @Override
            public void handleEvent(DOMEvent domEvent) {
                DOMDocument document = browser.getDocument();

                // get the input boxes
                DOMInputElement companyNameIn = (DOMInputElement) document.findElement(By.id("company-name"));
                DOMInputElement streetnameIn = (DOMInputElement) document.findElement(By.id("streetname"));
                DOMInputElement houseNumberIn = (DOMInputElement) document.findElement(By.id("housenumber"));
                DOMInputElement zipcodeIn = (DOMInputElement) document.findElement(By.id("zipcode"));
                DOMInputElement cityIn = (DOMInputElement) document.findElement(By.id("city"));
                DOMInputElement countryIn = (DOMInputElement) document.findElement(By.id("country"));
                
                // get the values
                String companyName = companyNameIn.getValue();
                String streetname = streetnameIn.getValue();
                String houseNumberStr = houseNumberIn.getValue();
                String zipcodeStr = zipcodeIn.getValue();
                String city = cityIn.getValue();
                String country = countryIn.getValue();
                
				// check that all fields in a row are filled
	            DOMElement fieldEmpty = document.findElement(By.id("fieldEmpty"));
				if (companyName.trim().isEmpty() | streetname.trim().isEmpty() | houseNumberStr.trim().isEmpty() |
						zipcodeStr.trim().isEmpty() | city.trim().isEmpty() | country.trim().isEmpty()) {

	                //prompt an failed message
	                fieldEmpty.setAttribute("class", "alert alert-danger");

				} else { 

					int houseNumber = Integer.parseInt(houseNumberStr);
					int zipcode = Integer.parseInt(zipcodeStr);
                
					// reset the input boxes
					companyNameIn.setValue("");
					streetnameIn.setValue("");
					houseNumberIn.setValue("");
					zipcodeIn.setValue("");
					cityIn.setValue("");
					countryIn.setValue("");
					
					Address address = new Address(streetname, houseNumber, zipcode, city, country);
					Customer customer = new Customer(companyName, address, new Order("Placeholder", new FixRate(new BasicOrderComponent()), "Some text"));
					storage.saveCustomer(customer);
					
					addCustomerHTML(companyName);
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");
	                
	        		CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
	        		executorService.execute(customerRegistrationView::loadView);
				}
            }
        }, false); 
 	
    }
    
    /**
     * initializes the buttons of the customer registration view in order to create a new order for a specific customer
     * @param browser
     */
    private void initNewOrderButtons(){
        List<DOMElement> newOrderButtons = this.browser.getDocument().findElements(By.xpath("//button[contains(@class, 'new-order')]"));
        for (DOMElement newOrderButton : newOrderButtons) {
            newOrderButton.addEventListener(DOMEventType.OnClick, domEvent -> {
            	String companyName = newOrderButton.getParent().getTextContent();
            	companyName = companyName.replace("Neuer Auftrag", "");
            	companyName = companyName.trim();
            	Customer customer = storage.findCustomersByCompanyName(companyName);
            	OrderCreationView orderCreationView = new OrderCreationView(this.browser, customer);

            	this.executorService.execute(orderCreationView::loadView);
            	
            }, false);
        }
    }
    
    private void initEditOrderButtons() {
    	List<DOMElement> editButtons = this.browser.getDocument().findElements(By.xpath("//button[contains(@class, 'edit')]"));
    	for (DOMElement editButton : editButtons) {
    		editButton.addEventListener(DOMEventType.OnClick, domEvent -> {
    			
    			DOMElement li = (DOMElement) editButton.getParent().getParent();
        		String orderIdStr = li.findElement(By.xpath("span/span")).getTextContent().replace("(", "").replace(")", "");
        		int orderId = Integer.parseInt(orderIdStr.trim());
        		Order order = storage.findOrderByID(orderId);
        		
        		OrderCreationView orderCreationView = new OrderCreationView(this.browser, order);
        		this.executorService.execute(orderCreationView::loadView);
        		
    		}, false);
    	}
    }
}
