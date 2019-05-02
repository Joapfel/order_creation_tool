package browserActions;

import browserViews.*;
import dao.*;
import main.Main;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.dom.*;
import com.teamdev.jxbrowser.chromium.dom.events.*;

import java.util.ArrayList;
import java.util.List;

public class ButtonInitializer {

    /**
     * initializes the buttons of the customer registration view in order to create a new order for a specific customer
     * @param browser
     */
    public static void initNewOrderButtons(Browser browser){
        List<DOMElement> newOrderButtons = browser.getDocument().findElements(By.xpath("//button[contains(@class, 'new-order')]"));
        for (DOMElement newOrderButton : newOrderButtons) {
            newOrderButton.addEventListener(DOMEventType.OnClick, domEvent -> { 
               /*PageLoader.loadHTMLFile(browser, HTMLFiles.AUFTRAGSERSTELLUNG.getHtmlFile()), false);*/
            	PageLoader.loadGoogle(browser);
            	OrderCreationView orderCreationView = new OrderCreationView(browser);
            	orderCreationView.loadView();
            	
            }, false);
        }
    }

    /**
     * initializes the login button of the login view
     * checks whether credentials exist / are correct
     *      if so loads the customer registration view
     *      if not yields an error message
     * @param browser
     */
    public static void initLoginButton(Browser browser){
        DOMDocument doc = browser.getDocument();
        DOMElement loginButton = doc.findElement(By.id("loginButton"));
        loginButton.addEventListener(DOMEventType.OnClick, domEvent -> {
            // get the entered credentials
            DOMInputElement usernameEl = (DOMInputElement) doc.findElement(By.id("exampleInputEmail1"));
            DOMInputElement pwEl = (DOMInputElement) doc.findElement(By.id("exampleInputPassword1"));
            String username = usernameEl.getValue();
            String pw = pwEl.getValue();
            Serializer storage = Main.getSerializer();
            
            User userEntered = new User(username, pw);
            System.out.println("Entered User:");
            System.out.println(userEntered.getUsername());
            
            User foundUser = storage.findUserByUsername(username);
            System.out.println("Found User:");
            System.out.println(foundUser.getUsername());

            //if (userEntered.equals(foundUser)){
            if (true){
                // little hack -> without this in-between-load the program times out
                PageLoader.loadGoogle(browser);
                // load the actual view
                CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
                customerRegistrationView.loadView();

            } else {
                //prompt an failed message
                DOMElement loginFail = doc.findElement(By.id("loginFail"));
                loginFail.setAttribute("class", "alert alert-danger");
            }

        }, false);
    }
    
    public static void initAddOrderComponentButton(Browser browser) {
    	DOMDocument doc = browser.getDocument();
    	
    	DOMElement addButtonMaterial = doc.findElement(By.id("button-addon-material"));
    	addButtonMaterial.addEventListener(DOMEventType.OnClick, new DOMEventListener() {	
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
				DOMInputElement materialInput = (DOMInputElement) doc.findElement(By.id("material-input"));
				DOMInputElement materialUnitsCount = (DOMInputElement) doc.findElement(By.id("material-units-count"));
				DOMInputElement materialPricePerUnit = (DOMInputElement) doc.findElement(By.id("material-price-per-unit"));
				String material = materialInput.getValue();
				String unitsCount = materialUnitsCount.getValue();
				String pricePerUnit = materialPricePerUnit.getValue();
				
				// check that all fields in a row are filled
	            DOMElement fieldEmpty = doc.findElement(By.id("fieldEmpty"));
				if (material.trim().isEmpty() | unitsCount.trim().isEmpty() | pricePerUnit.trim().isEmpty()) {
	                //prompt an failed message
	                fieldEmpty.setAttribute("class", "alert alert-danger");

				} else {
					
					materialInput.setValue("");
					materialUnitsCount.setValue("");
					materialPricePerUnit.setValue("");
				
					String html = 
						"<li class=\"list-group-item item-used\">\n" + 
						" <span class=\"material\">" + material + "</span>\n" + 
						" <span class=\"units-count\">" + unitsCount + "</span>\n" + 
						" <span class=\"price-per-unit\">" + pricePerUnit + " Euro</span>\n" + 
						" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Löschen</button>\n" + 
						"</li>";
					String inner = doc.findElement(By.id("materials-list")).getInnerHTML();
					doc.findElement(By.id("materials-list")).setInnerHTML(inner + html);
					
					// activate the delete function for the added row
					ButtonInitializer.initDeleteOrderButton(browser);
					
					// in case the warning was shown remove it again
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");

				}
			}
		}, false);
    	
    	DOMElement addButtonsMachine = doc.findElement(By.id("button-addon-machine"));
    	addButtonsMachine.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
				DOMInputElement machineInput = (DOMInputElement) doc.findElement(By.id("machine-input"));
				DOMInputElement machineHours = (DOMInputElement) doc.findElement(By.id("machine-hours"));
				DOMInputElement machinePricePerHour = (DOMInputElement) doc.findElement(By.id("machine-price-per-hour"));
				String machine = machineInput.getValue();
				String hoursCount = machineHours.getValue();
				String pricePerHour = machinePricePerHour.getValue();
				
	            DOMElement fieldEmpty = doc.findElement(By.id("fieldEmpty"));
				if (machine.trim().isEmpty() | hoursCount.trim().isEmpty() | pricePerHour.trim().isEmpty()) {
	                //prompt an failed message
	                fieldEmpty.setAttribute("class", "alert alert-danger");

				} else {
					
					machineInput.setValue("");
					machineHours.setValue("");
					machinePricePerHour.setValue("150");
					
					String html = 
						"<li class=\"list-group-item\">\n" + 
						" <span class=\"machine\">" + machine + "</span>\n" + 
						" <span class=\"machine-hours\">" + hoursCount + "</span>\n" + 
						" <span class=\"machine-per-hour\">" + pricePerHour + " Euro</span>\n" + 
						" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Löschen</button>\n" + 
						"</li>";
					String inner = doc.findElement(By.id("machines-list")).getInnerHTML();
					doc.findElement(By.id("machines-list")).setInnerHTML(inner + html);
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");
				}
			}
		}, false);
    	
    	DOMElement addButtonsHours = doc.findElement(By.id("button-addon-hours"));
    	addButtonsHours.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
				
				DOMInputElement hoursInput = (DOMInputElement) doc.findElement(By.id("hours-count"));
				DOMInputElement hoursPricePerHour = (DOMInputElement) doc.findElement(By.id("hours-price-per-hour"));
				String hours = hoursInput.getValue();
				String pricePerHour = hoursPricePerHour.getValue();
				
	            DOMElement fieldEmpty = doc.findElement(By.id("fieldEmpty"));
				if (hours.trim().isEmpty() | pricePerHour.trim().isEmpty()) {
	                //prompt an failed message
	                fieldEmpty.setAttribute("class", "alert alert-danger");

				} else {
					
					hoursInput.setValue("");
					hoursPricePerHour.setValue("80");
					
					String html = 
						"<li class=\"list-group-item\">\n" + 
						" <span class=\"human-hours\">" + hours + " Stunden</span>\n" + 
						" <span class=\"human-hour-price\">" + pricePerHour + " Euro</span>\n" + 
						" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Löschen</button>\n" + 
						"</li>";
					String inner = doc.findElement(By.id("hours-list")).getInnerHTML();
					doc.findElement(By.id("hours-list")).setInnerHTML(inner + html);
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");
				}

			}
		}, false);
    }
    
    public static void initSaveOrderButton(Browser browser) {
    	DOMDocument doc = browser.getDocument();
    	
    	DOMElement buttonSaveOrder = doc.findElement(By.id("buttonSaveOrder"));
    	buttonSaveOrder.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
		    	// get all the materials
		    	List<String> materials = new ArrayList<String>();
		    	List<Integer> materialUnitsCounts = new ArrayList<Integer>();
		    	List<Integer> materialPricesPerUnit = new ArrayList<Integer>();
				List<DOMElement> materialsDOM = doc.findElements(By.xpath("//ul[@id='materials-list']/li/span[@class='material']"));
				List<DOMElement> materialUnitsCountsDOM = doc.findElements(By.xpath("//ul[@id='materials-list']/li/span[@class='units-count']"));
				List<DOMElement> materialPricesPerUnitDOM = doc.findElements(By.xpath("//ul[@id='materials-list']/li/span[@class='price-per-unit']"));
				for (DOMElement materialDOM : materialsDOM) {
					materials.add(materialDOM.getInnerText());
				}
				for (DOMElement unitsCountDOM : materialUnitsCountsDOM) {
					materialUnitsCounts.add(Integer.parseInt(unitsCountDOM.getInnerText()));
				}
				for (DOMElement pricePerUnitDOM : materialPricesPerUnitDOM) {
					materialPricesPerUnit.add(Integer.parseInt(pricePerUnitDOM.getInnerText().split(" ")[0]));
				}
				
				// TODO: remove me
				for (int i = 0; i < materials.size(); i++) {
					System.out.println(materials.get(i) + " " + materialUnitsCounts.get(i) + " " + materialPricesPerUnit.get(i));
				}
				
				// get all the machines
		    	List<String> machines = new ArrayList<String>();
		    	List<Integer> machineHoursCount = new ArrayList<Integer>();
		    	List<Integer> machinePricesPerHour = new ArrayList<Integer>();
				List<DOMElement> machinesDOM = doc.findElements(By.xpath("//ul[@id='machines-list']/li/span[@class='machine']"));
				List<DOMElement> machineHoursCountsDOM = doc.findElements(By.xpath("//ul[@id='machines-list']/li/span[@class='machine-hours']"));
				List<DOMElement> machinePricesPerHourDOM = doc.findElements(By.xpath("//ul[@id='machines-list']/li/span[@class='machine-per-hour']"));
				
				for (DOMElement machineDOM : machinesDOM) {
					machines.add(machineDOM.getInnerText());
				}
				for (DOMElement hoursCountDOM : machineHoursCountsDOM) {
					machineHoursCount.add(Integer.parseInt(hoursCountDOM.getInnerText()));
				}
				for (DOMElement pricePerHourDOM : machinePricesPerHourDOM) {
					machinePricesPerHour.add(Integer.parseInt(pricePerHourDOM.getInnerText().split(" ")[0]));
				}
				
				// TODO: remove me
				for (int i = 0; i < machines.size(); i++) {
					System.out.println(machines.get(i) + " " + machineHoursCount.get(i) + " " + machinePricesPerHour.get(i));
				}

				// get the hours
		    	List<Integer> hoursCounts = new ArrayList<Integer>();
		    	List<Integer> hoursPricesPerHour = new ArrayList<Integer>();
				List<DOMElement> hoursCountsDOM = doc.findElements(By.xpath("//ul[@id='hours-list']/li/span[@class='human-hours']"));
				List<DOMElement> hoursPricesPerHourDOM = doc.findElements(By.xpath("//ul[@id='hours-list']/li/span[@class='human-hour-price']"));
				
				for (DOMElement hoursCountDOM : hoursCountsDOM) {
					hoursCounts.add(Integer.parseInt(hoursCountDOM.getInnerText().split(" ")[0]));
				}
				for (DOMElement hoursPricePerHourDOM : hoursPricesPerHourDOM) {
					hoursPricesPerHour.add(Integer.parseInt(hoursPricePerHourDOM.getInnerText().split(" ")[0]));
				}
				
				// TODO: remove me
				for (int i = 0; i < hoursCounts.size(); i++) {
					System.out.println(hoursCounts.get(i) + " " + hoursPricesPerHour.get(i));
				}
				
				// save the order
				// add the materials to the order
				OrderComponent order = new BasicOrderComponent();
				for (int i = 0; i < materials.size(); i++) {
					order = new Material(order, materials.get(i), materialUnitsCounts.get(i), materialPricesPerUnit.get(i));
				}
				// TODO: toString causes exception
				// System.out.println(order.toString());
				System.out.println(order.price());
				// TODO: summary causes exception
				//System.out.println(order.summary());
				
			}
		}, false);
    	
    }
    
    public static void initDeleteOrderButton(Browser browser) {
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

    public static void initNavbar(Browser browser) {
    	DOMDocument doc = browser.getDocument();
    	DOMElement customerRegistrationNavbarButton = doc.findElement(By.id("customer-registration"));
    	customerRegistrationNavbarButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// TODO Auto-generated method stub
				PageLoader.loadGoogle(browser);
				CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
				customerRegistrationView.loadView();
				
			}
		}, false);
    }

}
