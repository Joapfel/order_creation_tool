package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;

import browserActions.*;
import utils.*;

import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;

public class CustomerRegistrationView implements View{

    private Browser browser;

    public CustomerRegistrationView(Browser browser){
        this.browser = browser;
    }

    @Override
    public void loadView() {

        PageLoader.loadHTMLFileComplete(this.browser, HTMLFiles.KUNDENERFASSUNG.getHtmlFile());
        ButtonInitializer.initNewOrderButtons(this.browser);
        
        // save the customer
        DOMElement saveButton = browser.getDocument().findElement(By.id("save-customer"));
        saveButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
            @Override
            public void handleEvent(DOMEvent domEvent) {
                DOMDocument document = browser.getDocument();

                // get the input boxes
                DOMInputElement companyNameIn = (DOMInputElement) document.findElement(By.id("company-name"));
                DOMInputElement streetnameIn = (DOMInputElement) document.findElement(By.id("streetname"));
                DOMInputElement houseNumberIn = (DOMInputElement) document.findElement(By.id("housenumber"));
                DOMInputElement zipcodeIn = (DOMInputElement) document.findElement(By.id("zipcode"));
                DOMInputElement cityIn = (DOMInputElement) document.findElement(By.id("city"));
                DOMInputElement countryIn = (DOMInputElement) document.findElement(By.id("country"));
                
                // get the values
                // TODO: handle missing values
                String companyName = companyNameIn.getValue();
                String streetname = streetnameIn.getValue();
                int houseNumber = Integer.parseInt(houseNumberIn.getValue());
                int zipcode = Integer.parseInt(zipcodeIn.getValue());
                String city = cityIn.getValue();
                String country = countryIn.getValue();
                
                // reset the input boxes
                companyNameIn.setValue("");
                streetnameIn.setValue("");
                houseNumberIn.setValue("");
                zipcodeIn.setValue("");
                cityIn.setValue("");
                countryIn.setValue("");
                
                System.out.println(companyName + " " + streetname + " " + houseNumber + " " + zipcode + " " + city + " " + country);

                String html =
                        "<div class='card'>\n" +
                        "  <div class='card-header' id='headingFour'>\n" +
                        "    <h2 class='mb-0'>\n" +
                        "      <button class='btn btn-link collapsed' type='button' data-toggle='collapse' data-target='#collapseFour' aria-expanded='false' aria-controls='collapseFour'>\n" +
                        "        <span class='oi oi-caret-bottom'></span>\n" +
                        "      </button>\n" +
                                companyName +
                        "      <button class='btn btn-primary my-2 my-sm-0 float-right new-order' type='submit'>Neuer Auftrag</button>\n" +
                        "    </h2>\n" +
                        "  </div>\n" +
                        "</div>\n";
                String inner = document.findElement(By.id("accordionExample")).getInnerHTML();
                document.findElement(By.id("accordionExample")).setInnerHTML(inner + html);

                ButtonInitializer.initNewOrderButtons(browser);
            }
        }, false); }
}
