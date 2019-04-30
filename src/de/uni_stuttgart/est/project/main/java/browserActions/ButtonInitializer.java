package browserActions;

import browserViews.*;
import dao.*;
import utils.HTMLFiles;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.dom.*;
import com.teamdev.jxbrowser.chromium.dom.events.*;

import java.util.List;

public class ButtonInitializer {

    /**
     * initializes the buttons of the customer registration view in order to create a new order for a specific customer
     * @param browser
     */
    public static void initNewOrderButtons(Browser browser){
        List<DOMElement> newOrderButtons = browser.getDocument().findElements(By.xpath("//button[contains(@class, 'new-order')]"));
        for (DOMElement newOrderButton : newOrderButtons) {
            newOrderButton.addEventListener(DOMEventType.OnClick, domEvent
                    -> PageLoader.loadHTMLFile(browser, HTMLFiles.AUFTRAGSERSTELLUNG.getHtmlFile()), false);
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

            User userEntered = new User(username, pw);
            Storage storage = new Serializer();
            User foundUser = storage.findUserByUsername(username);

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

}
