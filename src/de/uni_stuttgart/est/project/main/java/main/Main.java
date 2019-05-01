package main;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.*;

import javax.swing.*;
import browserViews.*;
import dao.Serializer;
import dao.StorageController;
import dao.User;

import java.awt.*;

public class Main {
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
        
        initialize(); 	//loading the db; not sure how to implement this into the larger frame; we should use one instance of Serializer to keep everything consitent
        				// alternatively we could create a Serializer Constructor and load the Database there whenever a Serializer Object is created; Your call -PT

        LoginView loginView = new LoginView(browser);
        loginView.loadView();

        //CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
        //customerRegistrationView.loadView();

    }
    
    /**
     * This method will create a user "Testuser" with the password "est" if the database doesn't contain this user already.
     * Additionally the database is loaded for further use.
     * 
     * @author Philipp
     *
     */
    private static Serializer initialize() {
    	String name = "Testuser";
    	String pw = "est";
    	
    	User user = new User(name, pw);
    	
    	Serializer sers = new Serializer();
    	sers = StorageController.loadDB();
    	
    	if(sers.userExists(user)) {
    		
    	}
    	else {
    		sers.saveUser(user);
    	}
    	
    	return sers;
    }
}