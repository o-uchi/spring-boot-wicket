package wicket.web;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Home extends WebPage {

	public Home() {
		add(new Label("title", "this is the homepage"));
		add(new BookmarkablePageLink<String>("link", MountedPage.class));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
	}
}
