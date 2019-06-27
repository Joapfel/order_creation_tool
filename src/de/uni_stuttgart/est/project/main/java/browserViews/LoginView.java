package browserViews;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import authentication.BasicAuthentication;
import browserActions.*;
import dao.User;
import main.Initialize;
import storage.Storage;
import utils.*;

/**
 * 
 * @author johannes
 *
 */
public class LoginView implements View{

    private final Browser browser;
    private final ExecutorService executorService;
    private Storage storage = Initialize.getSerializer();

    public LoginView(final Browser browser){
        this.browser = browser;
        this.executorService = Executors.newCachedThreadPool();
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
            BasicAuthentication auth = new BasicAuthentication();
            
            if (auth.login(username, pw)){
            	
            	User user = storage.findUserByUsername(username);
            	String role = user.getRole();
            	
            	if (role.equals("normal")){
        			// load the actual view
        			CustomerRegistrationView customerRegistrationView = new CustomerRegistrationView(this.browser);
        			this.executorService.execute(customerRegistrationView::loadView);
        			
        		} else if (role.equals("HR user")) {
        			HRView hrView = new HRView(this.browser);
                	this.executorService.execute(hrView::loadView);
        		}
                
            } else {
                //prompt an failed message
                DOMElement loginFail = doc.findElement(By.id("loginFail"));
                loginFail.setAttribute("class", "alert alert-danger");
            }

        }, false);
    }
 
}
