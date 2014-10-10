package wicket;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wicket.service.TextService;
import wicket.web.Home;
import wicket.web.MountedPage;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class WicketWebApplicationTest {

	@Resource
	private WicketTester wicketTester;

	@Test
	public void testApplication() {
		wicketTester.startPage(Home.class);
		wicketTester.assertRenderedPage(Home.class);
	}

	@Test
	public void testMountedPage() {
		wicketTester.startPage(MountedPage.class);
		wicketTester.assertRenderedPage(MountedPage.class);
		wicketTester.assertLabel("serviceText", "test2");
	}
}

@Configuration
class TestConfig {

	@Resource
	private WicketWebApplication wicketWebApplication;

	@Resource
	private ApplicationContextMock mock;

	@Bean
	public WicketTester wicketTesterFactoryBean() {
		SpringComponentInjector.setDefaultContext(wicketWebApplication, mock);
		return new WicketTester(wicketWebApplication);
	}

	@Mock
	private TextService textServiceMock;

	@Bean
	public ApplicationContextMock applicationContextMockBean() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(textServiceMock.getText()).thenReturn("test2");
		ApplicationContextMock applicationContextMock = new ApplicationContextMock();
		applicationContextMock.putBean("textService", textServiceMock);
		return applicationContextMock;
	}
}
