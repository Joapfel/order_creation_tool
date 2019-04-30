package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;

import browserActions.*;
import utils.*;

public class LoginView implements View{

    private final Browser browser;

    public LoginView(final Browser browser){
        this.browser = browser;
    }

    @Override
    public void loadView() {
        PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.ANMELDUNG_FORM.getHtmlFile());
        ButtonInitializer.initLoginButton(this.browser);
    }
}
