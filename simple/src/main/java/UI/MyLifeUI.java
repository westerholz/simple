package UI;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme("MyNewTheme")
@PreserveOnRefresh
public class MyLifeUI extends UI{
	private Map<String,Object> dbMap;
	private EntityManager em;
	private MainScreen mainScreen;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyLifeUI.class)
	public static class SimpleServlet extends VaadinServlet {		
	}
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("MyLife in one Place!");
		//Map<String, String> env = System.getenv("DB_USER");
		dbMap = new HashMap<String, Object>();
		//Persistence.createEntityManagerFactory("simple").
		dbMap.put("hibernate.connection.password", System.getenv("DB_PW"));
		dbMap.put("hibernate.connection.user", System.getenv("DB_USER"));
		em = Persistence.createEntityManagerFactory("simple", dbMap).createEntityManager();
		//dbMap = Persistence.createEntityManagerFactory("simple").getProperties();
		
		//em = Persistence.createEntityManagerFactory("simple").createEntityManager();
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
