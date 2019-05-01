package browserViews;

import com.teamdev.jxbrowser.chromium.Browser;

import browserActions.ButtonInitializer;
import browserActions.PageLoader;
import utils.HTMLFiles;

public class OrderCreationView implements View {
	
	private Browser browser;
	
	public OrderCreationView(Browser browser) {
		// TODO Auto-generated constructor stub
		this.browser = browser;
	}

	@Override
	public void loadView() {
		// TODO Auto-generated method stub
		PageLoader.loadHTMLFileComplete(browser, HTMLFiles.AUFTRAGSERSTELLUNG.getHtmlFile());
		ButtonInitializer.initAddOrderComponentButton(browser);
		ButtonInitializer.initSaveOrderButton(browser);
	}

}
