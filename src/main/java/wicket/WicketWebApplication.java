package wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wicket.service.TextService;
import wicket.web.Home;
import wicket.web.MountedPage;

import javax.annotation.Resource;

@Component
public class WicketWebApplication extends WebApplication {

	private final static Logger logger = LoggerFactory.getLogger(WicketWebApplication.class);

	@Resource
	private TextService exampleService;

	@Override
	public Class<? extends Page> getHomePage() {
		return Home.class;
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		mountPage("/mounted.html", MountedPage.class);
		logger.info(exampleService.getText());
	}
}
