package UI;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

import kaesdingeling.hybridmenu.HybridMenu;
import kaesdingeling.hybridmenu.components.HMButton;
import kaesdingeling.hybridmenu.components.HMLabel;
import kaesdingeling.hybridmenu.components.HMSubMenu;
import kaesdingeling.hybridmenu.components.LeftMenu;
import kaesdingeling.hybridmenu.components.TopMenu;
import kaesdingeling.hybridmenu.data.MenuConfig;
import kaesdingeling.hybridmenu.design.DesignColor;
import kaesdingeling.hybridmenu.design.DesignItem;
import kaesdingeling.hybridmenu.components.Notification;
import kaesdingeling.hybridmenu.components.NotificationCenter;

import persistence.User;

public class MainScreen extends HorizontalLayout implements View {
	private HybridMenu hybridMenu;
	private LeftMenu leftMenu;
	private TopMenu topMenu;
	private MyLifeUI ui;
	private User loggedInUser;
	private HMButton loginViewButton;
	private ResourceBundle menuLabelsBundle;
	private Map<String,HMSubMenu> leftSubMenues;

	public MainScreen(MyLifeUI ui) {
		//ApplicationContext context = new AnnotationConfigApplicationContext(); 
		//context.
		this.ui = ui;
		initializeUI();
		leftSubMenues = new HashMap<String,HMSubMenu>();
		menuLabelsBundle = ResourceBundle.getBundle("i18n.menuLabels",
                VaadinSession.getCurrent().getLocale());
		defineUnauthorizedMenu();
		UI.getCurrent().getNavigator().setErrorView(ErrorView.class);
        setSizeFull();
        
	}

	protected void defineUnauthorizedMenu() {
		
		loginViewButton = HMButton.get().withIcon(VaadinIcons.USER).withCaption("Login").withNavigateTo(LoginView.class);
		leftMenu.add(loginViewButton);
	    
		HMSubMenu memberList = leftMenu.add(HMSubMenu.get().withCaption("Member").withIcon(VaadinIcons.USERS));
		leftSubMenues.put("memberList", memberList);
		memberList.add(
				HMButton.get().withCaption("Settings").withIcon(VaadinIcons.COGS).withNavigateTo(LogoutView.class));
	}
    public void successfullLogin() {
    	loggedInUser = (User) UI.getCurrent().getSession().getAttribute("user");
		if (loggedInUser == null) {
			return;
		}
		defineMenuAfterLogin();
		
		
    }
	private void defineMenuAfterLogin() {
		 
		
		// LeftMenu
		leftMenu.remove(loginViewButton);
		leftMenu.remove(leftSubMenues.get("memberList"));
		HMButton overviewViewButton = HMButton.get().withCaption(OverviewView.VIEW_NAME).withIcon(VaadinIcons.HOME)
				.withNavigateTo(OverviewView.class);
		leftMenu.add(overviewViewButton);
		
		HMSubMenu accountsSubMenu = leftMenu.add(HMSubMenu.get().withCaption(menuLabelsBundle.getString("accountSubMenu")).withIcon(VaadinIcons.ACCORDION_MENU));
		leftSubMenues.put("accounts", accountsSubMenu);
		HMButton accountOverviewButton = HMButton.get().withCaption(menuLabelsBundle.getString("accountOverviewButton")).withIcon(VaadinIcons.BUILDING).withNavigateTo(AccountOverviewView.class);
		accountsSubMenu.add(accountOverviewButton);
		

		// TopMenu
		HMLabel userLabel = HMLabel.get().withIcon(VaadinIcons.USER);
		userLabel.setDescription("Hi, " + loggedInUser.getLoginName());

		HMButton logoutButton = HMButton.get().withIcon(VaadinIcons.POWER_OFF).withDescription("Logout")
				.withNavigateTo(LogoutView.class);

		logoutButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
		      handleLogOut();		
			}
		});
		topMenu.add(userLabel);
		topMenu.add(logoutButton);
		overviewViewButton.click();
	}

	public void handleLogOut() {
		UI.getCurrent().getSession().setAttribute("user", null);
		removeAllComponents();
		initializeUI();
		defineUnauthorizedMenu();
		//ui.getNavigator().navigateTo(LogoutView.class);

	}

	private void initializeUI() {
		hybridMenu = null;
		leftMenu = null;
		topMenu = null;
		hybridMenu = HybridMenu.get().withNaviContent(new VerticalLayout())
				.withConfig(MenuConfig.get().withDesignItem(DesignItem.getWhiteDesign())).build();
		leftMenu = hybridMenu.getLeftMenu();
		topMenu = hybridMenu.getTopMenu();
		leftMenu.add(HMLabel.get().withCaption("<b>MyLife</b> Menu")).withIcon(VaadinIcons.BAR_CHART);
		UI.getCurrent().getSession().setAttribute("menu", this);
		
		addComponent(hybridMenu);
	}
}
