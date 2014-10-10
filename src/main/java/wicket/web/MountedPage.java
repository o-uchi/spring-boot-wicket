package wicket.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import wicket.service.TextService;

import javax.inject.Inject;

public class MountedPage extends WebPage {

	@Inject
	private TextService textService;

	public MountedPage() {
		add(new Label("title", "this is a mounted page"));
		add(new BookmarkablePageLink<String>("link", Home.class));
		add(new Label("serviceText", textService.getText()));
	}
}
