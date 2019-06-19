package browserActions;

import com.teamdev.jxbrowser.chromium.*;

import utils.PathUtils;

/**
 * 
 * @author johannes
 *
 */
public class PageLoader {

    public static void loadHTMLFile(Browser browser, String filename){
        String current = PathUtils.getCurrentDir();
        browser.loadURL("file:///" + current + "/src/de/uni_stuttgart/est/project/main/resources/web/" + filename);
    }

    public static void loadHTMLFileComplete(Browser browser, String filename){
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser value) {
                loadHTMLFile(value, filename);
            }
        });
    }
}