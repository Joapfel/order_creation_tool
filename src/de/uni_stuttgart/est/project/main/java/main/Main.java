package main;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.*;

import javax.swing.*;
import browserViews.*;
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

        LoginView loginView = new LoginView(browser);
        loginView.loadView();

        //CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(browser);
        //customerRegistrationView.loadView();

    }
}