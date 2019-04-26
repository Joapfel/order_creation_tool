package BrowserActions;

import Utils.PathUtils;
import com.teamdev.jxbrowser.chromium.*;

public class PageLoader {

    public static void loadHTMLFile(final Browser browser, final String filename){
        String current = PathUtils.getCurrentDir();
        browser.loadURL("file:///" + current + "/src/de/uni_stuttgart/est/project/main/resources/web/" + filename);
    }

    public static void loadHTMLFileComplete(final Browser browser, final String filename){
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser value) {
                loadHTMLFile(browser, filename);
            }
        });
    }
}
