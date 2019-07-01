package main;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.*;

import javax.swing.*;
import browserViews.*;
import output.ReportOutput;

import java.awt.*;

public class Main {
		
    public static void main(String[] args) {
    	
    	if(args.length > 0 && args[0].equals("report")) {
    		
    		String path = "Report.txt";
    		if (args.length > 1 && !args[1].isEmpty() && !args[1].equals(null)) {
    			path = args[1];
    		}
    		
    		ReportOutput.createReport(path);
    		System.out.println("Report created.");
    		
    	} else {
    		
            // general browser settings
            final Browser browser = new Browser();
            BrowserView browserView = new BrowserView(browser);
            JFrame frame = new JFrame("JxBrowser");
            frame.add(browserView, BorderLayout.CENTER);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

            LoginView loginView = new LoginView(browser);
            loginView.loadView();
    		
    	}
    }
}