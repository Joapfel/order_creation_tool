package browserViews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.DOMNode;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import browserActions.NavbarInitializer;
import browserActions.PageLoader;
import dao.BasicOrderComponent;
import dao.Customer;
import dao.Machine;
import dao.Material;
import dao.Order;
import dao.OrderComponent;
import dao.WorkingHours;
import main.Initialize;
import storage.Storage;
import utils.HTMLFiles;

/**
 * 
 * @author johannes
 *
 */
public class OrderCreationView implements View {
	
	private Browser browser;
	private Order savedOrder;
	private Customer customer;
	private final ExecutorService executorService;
	
	/**
	 * this constructor is used for creating a new order
	 * @param browser
	 */
	public OrderCreationView(Browser browser, Customer customer) {
		// TODO Auto-generated constructor stub
		this.browser = browser;
		this.savedOrder = null;
		this.customer = customer;
		this.executorService = Executors.newCachedThreadPool();
	}
	
	/**
	 * this constructor is used when loading an existing order into the view
	 * @param browser
	 */
	public OrderCreationView(Browser browser, Order order) {
		// TODO Auto-generated constructor stub
		this.browser = browser;
		this.savedOrder = order;
		this.executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void loadView() {
		// TODO Auto-generated method stub
		PageLoader.loadHTMLFileComplete(browser, HTMLFiles.AUFTRAGSERSTELLUNG.getHtmlFile());
		NavbarInitializer.initNavbar(browser);
		if (savedOrder != null) {
			loadExistingOrder();
		}
		initAddOrderComponentButton(browser);
		initSaveOrderButton(browser);
		initDeleteOrderButton(browser);
		initPreviewTabButton();
	}
	
	private void addMaterialHTML(String material, String unitsCount, String pricePerUnit) {
		DOMDocument doc = this.browser.getDocument();
		String html = 
				"<li class=\"list-group-item item-used\">\n" + 
				" <span class=\"material\">" + material + "</span>\n" + 
				" <span class=\"units-count\">" + unitsCount + "</span>\n" + 
				" <span class=\"price-per-unit\">" + pricePerUnit + " Euro</span>\n" + 
				" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n" + 
				"</li>";
			String inner = doc.findElement(By.id("materials-list")).getInnerHTML();
			doc.findElement(By.id("materials-list")).setInnerHTML(inner + html);
	}
	
	private void addMachineHTML(String machine, String hoursCount, String pricePerHour) {
		DOMDocument doc = this.browser.getDocument();
		String html = 
				"<li class=\"list-group-item\">\n" + 
				" <span class=\"machine\">" + machine + "</span>\n" + 
				" <span class=\"machine-hours\">" + hoursCount + "</span>\n" + 
				" <span class=\"machine-per-hour\">" + pricePerHour + " Euro</span>\n" + 
				" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n" + 
				"</li>";
			String inner = doc.findElement(By.id("machines-list")).getInnerHTML();
			doc.findElement(By.id("machines-list")).setInnerHTML(inner + html);
	}
	
	private void addHoursHTML(String hours, String pricePerHour) {
		DOMDocument doc = this.browser.getDocument();
		String html = 
				"<li class=\"list-group-item\">\n" + 
				" <span class=\"human-hours\">" + hours + " Stunden</span>\n" + 
				" <span class=\"human-hour-price\">" + pricePerHour + " Euro</span>\n" + 
				" <button class=\"deleteButton btn btn-outline-danger my-2 my-sm-0 float-right\" type=\"button\">Loeschen</button>\n" + 
				"</li>";
			String inner = doc.findElement(By.id("hours-list")).getInnerHTML();
			doc.findElement(By.id("hours-list")).setInnerHTML(inner + html);
	}
	
	private void loadExistingOrder() {
		OrderComponent orderComponent = this.savedOrder.getOrderComponent();
		while (!(orderComponent instanceof BasicOrderComponent)) {
			if (orderComponent instanceof Material) {
				Material material = (Material) orderComponent;
				System.out.println(material.getDescription() + " " + material.getQuantity() + " " + material.getPricePerUnit());
				Double pricePerUnitDouble = (Double) material.getPricePerUnit();
				String pricePerUnit = Integer.toString(pricePerUnitDouble.intValue());
				addMaterialHTML(material.getDescription(), Integer.toString(material.getQuantity()), pricePerUnit);
				orderComponent = material.getOrderComponent();
				
			} else if (orderComponent instanceof Machine) {
				Machine machine = (Machine) orderComponent;
				System.out.println(machine.getDescription() + " " + machine.getHours() + " " + machine.getPricePerHour());
				Double pricePerHourDouble = (Double) machine.getPricePerHour();
				String pricePerHour = Integer.toString(pricePerHourDouble.intValue());
				addMachineHTML(machine.getDescription(), Integer.toString(machine.getHours()), pricePerHour);
				orderComponent = machine.getOrderComponent();
				
			} else if (orderComponent instanceof WorkingHours) {
				WorkingHours workingHours = (WorkingHours) orderComponent;
				System.out.println(workingHours.getDescription() + " " + workingHours.getHours() + " " + workingHours.getRatePerHour());
				Double ratePerHourDouble = (Double) workingHours.getRatePerHour();
				String ratePerHour = Integer.toString(ratePerHourDouble.intValue());
				addHoursHTML(Integer.toString(workingHours.getHours()), ratePerHour);
				orderComponent = workingHours.getOrderComponent();
			}
		}
		
	}
	
    private void initAddOrderComponentButton(Browser browser) {
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
				
					addMaterialHTML(material, unitsCount, pricePerUnit);
					
					// activate the delete function for the added row
					initDeleteOrderButton(browser);
					
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
					
					addMachineHTML(machine, hoursCount, pricePerHour);

					initDeleteOrderButton(browser);
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
					
					addHoursHTML(hours, pricePerHour);

					initDeleteOrderButton(browser);
	                fieldEmpty.setAttribute("class", "alert alert-danger invisible");
				}

			}
		}, false);
    }

	
    private void initSaveOrderButton(Browser browser) {
    	DOMDocument doc = browser.getDocument();
    	
    	DOMElement buttonSaveOrder = doc.findElement(By.id("buttonSaveOrder"));
    	buttonSaveOrder.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
			
			@Override
			public void handleEvent(DOMEvent arg0) {
				// get the order name
				DOMInputElement orderNameInput = (DOMInputElement) doc.findElement(By.id("order-name"));
				String orderName = orderNameInput.getValue();
				
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
				
				// save the order
				// add the materials to the order
				OrderComponent order = new BasicOrderComponent();
				for (int i = 0; i < materials.size(); i++) {
					order = new Material(order, materials.get(i), materialUnitsCounts.get(i), materialPricesPerUnit.get(i));
				}
				for (int i = 0; i < machines.size(); i++) {
					order = new Machine(order, machines.get(i), machineHoursCount.get(i), machinePricesPerHour.get(i));
				}
				for (int i = 0; i < hoursCounts.size(); i++) {
					order = new WorkingHours(order, "Arbeitszeit", hoursCounts.get(i), hoursPricesPerHour.get(i));
				}
				Storage storage = Initialize.getSerializer();
				Order saveOrder = new Order(orderName, order, order.summary());
				if (customer != null) {
					customer.addOrder(saveOrder);
					storage.saveCustomer(customer);
					
				} else if (savedOrder != null) {
					int orderId = savedOrder.getOrderID();
					saveOrder.setOrderID(orderId);
					storage.saveOrder(saveOrder);
				}

				savedOrder = saveOrder;
				
				DOMElement warningPanel = doc.findElement(By.id("orderNotSaved"));
				warningPanel.setAttribute("class", "alert alert-danger invisible");
				
			}
		}, false);
    	
    }
    
    private void initDeleteOrderButton(Browser browser) {
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
	
	private void initPreviewTabButton() {
		DOMDocument doc = browser.getDocument();
		DOMElement orderPreviewLink = doc.findElement(By.id("order-preview-link"));
		orderPreviewLink.addEventListener(DOMEventType.OnClick, domEvent -> {

			// TODO Auto-generated method stub
			if (savedOrder != null) {
				OrderCreationViewPreview preview = new OrderCreationViewPreview(browser, savedOrder);
				this.executorService.execute(preview::loadView);
			} else {
				DOMElement warningPanel = doc.findElement(By.id("orderNotSaved"));
				warningPanel.setAttribute("class", "alert alert-danger");
			}
		}, false);

	}

}
