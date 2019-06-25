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
 * @author johannes
 *
 */
public class CustomerRegistrationView implements View{

    private Browser browser;
    private final ExecutorService executorService;

    public CustomerRegistrationView(Browser browser){
        this.browser = browser;
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void loadView() {

        PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.KUNDENERFASSUNG.getHtmlFile());
        initNewOrderButtons();
        initNewCustomerButton(); 
       
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
                
					System.out.println(companyName + " " + streetname + " " + houseNumber + " " + zipcode + " " + city + " " + country);
					
					Address address = new Address(streetname, houseNumber, zipcode, city, country);
					Customer customer = new Customer(companyName, address, new Order("Placeholder", new FixRate(new BasicOrderComponent()), "Some text"));
					Storage storage = new Serializer();
					storage.saveCustomer(customer);
					
					String html =
                        "<div class='card'>\n" +
                        "  <div class='card-header' id='headingFour'>\n" +
                        "    <h2 class='mb-0'>\n" +
                        "      <button class='btn btn-link collapsed' type='button' data-toggle='collapse' data-target='#collapseFour' aria-expanded='false' aria-controls='collapseFour'>\n" +
                        "        <span class='oi oi-caret-bottom'></span>\n" +
                        "      </button>\n" +
                                companyName +
                        "      <button class='btn btn-primary my-2 my-sm-0 float-right new-order' type='submit'>Neuer Auftrag</button>\n" +
                        "    </h2>\n" +
                        "  </div>\n" +
                        "</div>\n";
					String inner = document.findElement(By.id("accordionExample")).getInnerHTML();
					document.findElement(By.id("accordionExample")).setInnerHTML(inner + html);

					initNewOrderButtons();
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");
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
            	OrderCreationView orderCreationView = new OrderCreationView(this.browser);
            	this.executorService.execute(orderCreationView::loadView);
            	
            }, false);
        }
    }
}
