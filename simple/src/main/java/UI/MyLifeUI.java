package UI;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

@Theme("MyNewTheme")
@PreserveOnRefresh
public class MyLifeUI extends UI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> dbMap;
	private EntityManager em;
	private MainScreen mainScreen;
	private ResourceBundle labelBundle;
	private ResourceBundle messageBundle;
	public ResourceBundle getLabelBundle() {
		return labelBundle;
	}


	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	

	public ResourceBundle getMessageBundle() {
		return messageBundle;
	}


	public void setMessageBundle(ResourceBundle messageBundle) {
		this.messageBundle = messageBundle;
	}


	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyLifeUI.class)
	public static class SimpleServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;		
	}
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		labelBundle = 
	            ResourceBundle.getBundle("i18n.labels",
	                                     VaadinSession.getCurrent().getLocale());
		messageBundle = 
	            ResourceBundle.getBundle("i18n.messages",
	                                     VaadinSession.getCurrent().getLocale());
		getPage().setTitle(labelBundle.getString("browserTitle"));
		 
		        

		dbMap = new HashMap<String, Object>();

		dbMap.put("hibernate.connection.password", System.getenv("DB_PW"));
		dbMap.put("hibernate.connection.user", System.getenv("DB_USER"));
		dbMap.put("hibernate.connection.url", System.getenv("DB_URL"));
		em = Persistence.createEntityManagerFactory("simple", dbMap).createEntityManager();

		setContent( new MainScreen(this) );
	}
	
	
	@PostConstruct
	private void setMyContent() {
		setContent(mainScreen);
	}

	public static MyLifeUI get() {
		return (MyLifeUI) UI.getCurrent();
	}

	public Map<String, Object> getDbMap() {
		return dbMap;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
}
