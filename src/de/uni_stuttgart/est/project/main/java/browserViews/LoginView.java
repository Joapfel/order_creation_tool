package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import browserActions.*;
import dao.BasicAuthentication;
import dao.Serializer;
import dao.User;
import main.Main;
import utils.*;

public class LoginView implements View{

    private final Browser browser;

    public LoginView(final Browser browser){
        this.browser = browser;
    }

    @Override
    public void loadView() {
        PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.ANMELDUNG_FORM.getHtmlFile());
        initLoginButton();
    }
    
    /**
     * initializes the login button of the login view
     * checks whether credentials exist / are correct
     *      if so loads the customer registration view
     *      if not yields an error message
     * @param browser
     */
    private void initLoginButton(){
        DOMDocument doc = this.browser.getDocument();
        DOMElement loginButton = doc.findElement(By.id("loginButton"));
        loginButton.addEventListener(DOMEventType.OnClick, domEvent -> {
            // get the entered credentials
            DOMInputElement usernameEl = (DOMInputElement) doc.findElement(By.id("exampleInputEmail1"));
            DOMInputElement pwEl = (DOMInputElement) doc.findElement(By.id("exampleInputPassword1"));
            String username = usernameEl.getValue();
            String pw = pwEl.getValue();
            Serializer storage = Main.getSerializer();
            BasicAuthentication auth = new BasicAuthentication();
            
            User foundUser = storage.findUserByUsername(username);
           // System.out.println("Found User:");
           // System.out.println(foundUser.getUsername());

            if (auth.login(username, pw)){
                // little hack -> without this in-between-load the program times out
                PageLoader.loadGoogle(this.browser);
                // load the actual view
                CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(this.browser);
                customerRegistrationView.loadView();

            } else {
                //prompt an failed message
                DOMElement loginFail = doc.findElement(By.id("loginFail"));
                loginFail.setAttribute("class", "alert alert-danger");
            }

        }, false);
    }
 
}
