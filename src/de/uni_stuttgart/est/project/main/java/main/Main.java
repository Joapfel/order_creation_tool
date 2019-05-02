package main;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.*;

import javax.swing.*;
import browserViews.*;
import dao.BasicOrderComponent;
import dao.FixRate;
import dao.Machine;
import dao.Material;
import dao.OrderComponent;
import dao.Serializer;
import dao.StorageController;
import dao.User;
import dao.WorkingHours;

import java.awt.*;

public class Main {
		private static Serializer mainSerializer;
		
    public static void main(String[] args) {

        // general browser settings
        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        JFrame frame = new JFrame("JxBrowser");
        frame.add(browserView, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println(browser.toString());
        
        mainSerializer = initialize(); 	//loading the db; not sure how to implement this into the larger frame; we should use one instance of Serializer to keep everything consitent
        				// alternatively we could create a Serializer Constructor and load the Database there whenever a Serializer Object is created; Your call -PT

        testFunction();
        
        LoginView loginView = new LoginView(browser);
        loginView.loadView();

        //CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
        //customerRegistrationView.loadView();

    }
    
    public static Serializer getSerializer() {
    	return mainSerializer;
    }
    	
    
    /**
     * This method will create a user "Testuser" with the password "est" if the database doesn't contain this user already.
     * Additionally the database is loaded for further use.
     * 
     * @author Philipp
     *
     */
    private static Serializer initialize() {
    	/*
    	 * Testuser and his pw are meant to be implemented for first-time-login.
    	 * There should be a way to get rid of the password / to disable access with this account; that should be a future project and be done before shipping. 
    	 */
    	String name = "Testuser";
    	String pw = "est";
    	User user = new User(name, pw);
    	
    	/*
    	 * The Serializer and the loading of it should be moved to the interacting part of the program / the object should be pushed to that place.
    	 * All in a file stored information are loaded into a Serializer-Object for future interaction.
    	 */
    	Serializer sers = new Serializer();
    	if(StorageController.fileCheck()) {
    		sers = StorageController.loadDB();	//if the database-file is lost this would not find a file and throw an error
    	}
    	else {
    		StorageController.saveDB(sers); //therefor an empty file is created if database.ser is not found
    		System.out.println("A new database-file had to be created!");
    	}
    	
    	
    	/*
    	 * If the Testuser exists in the database he should not be recreated. 
    	 */
    	if(sers.userExists(user)) {
    		User uuser = sers.findUserByUsername(name);
    		String[] data = uuser.testGetUser(uuser);	// For Testing
    		System.out.println("Username: "+data[0]);	// For Testing
    		System.out.println("Password: "+data[1]);	// For Testing
    	}
    	else {
    		sers.saveUser(user);
    	}
    	
    	/*
    	 * After initializing this method returns the loaded Serializer-Object.
    	 */
    	return sers;
    }
    
    
    public static void testFunction() {
    	OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);

		double price = orderComp.price();
		String summary = orderComp.summary();
		
		System.out.println(price);
		
		System.out.println(summary);
		
    }
}