package Main;

import BrowserActions.ButtonInitializer;
import BrowserActions.PageLoader;
import Utils.HTMLFiles;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEvent;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventListener;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        JFrame frame = new JFrame("JxBrowser");
        frame.add(browserView, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        PageLoader.loadHTMLFileComplete(browser, HTMLFiles.KUNDENERFASSUNG.getHtmlFile());
        ButtonInitializer.initNewOrderButtons(browser);
        DOMElement saveButton = browser.getDocument().findElement(By.id("save-customer"));
        saveButton.addEventListener(DOMEventType.OnClick, new DOMEventListener() {
            @Override
            public void handleEvent(DOMEvent domEvent) {
                DOMDocument document = browser.getDocument();

                DOMInputElement in = (DOMInputElement) document.findElement(By.id("company-name"));
                String companyName = in.getValue();

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
        }, false);

    }
}