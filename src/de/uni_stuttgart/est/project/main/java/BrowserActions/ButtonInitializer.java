package BrowserActions;

import Utils.HTMLFiles;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;

import java.util.List;

public class ButtonInitializer {

    public static void initNewOrderButtons(final Browser browser){
        // TODO: handle the initialisation of html views in an object oriented way
        List<DOMElement> newOrderButtons = browser.getDocument().findElements(By.xpath("//button[contains(@class, 'new-order')]"));
        for (DOMElement newOrderButton : newOrderButtons) {
            newOrderButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
                @Override
                public void handleEvent(DOMEvent domEvent) {
                    PageLoader.loadHTMLFile(browser, HTMLFiles.AUFTRAGSERSTELLUNG.getHtmlFile());
                }
            }, false);
        }
    }

}
