package UI;

import com.vaadin.ui.Button.ClickEvent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;



import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import controller.LoginValidation;
import controller.PasswordHash;
import kaesdingeling.hybridmenu.HybridMenu;
import persistence.Credentials;
import persistence.User;

public class LoginView extends VerticalLayout implements View{
	public static final String viewName = "Login View";
	private MyLifeUI ui;
  public LoginView( ) {
	  ui = (MyLifeUI)UI.getCurrent();
	  TextField userName = new TextField(ui.getLabelBundle().getString("username"));
	  userName.setIcon(VaadinIcons.USER);
	  userName.setRequiredIndicatorVisible(true);
	  addComponent(userName);
	  
	  PasswordField passwordField = new PasswordField(ui.getLabelBundle().getString("password"));
	  passwordField.setIcon(VaadinIcons.STAR);
	  passwordField.setRequiredIndicatorVisible(true);
	  addComponent(passwordField);
	  
	  
	 Button loginButton = new Button(ui.getLabelBundle().getString("login"));
	 loginButton.addClickListener(new ClickListener() {
		 private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			User user = User.getUserByLoginName(userName.getValue());
			if (user != null) {
				if(LoginValidation.validatePassword(passwordField.getValue(), user)) {
					VaadinSession.getCurrent().setAttribute("user", user);
					//UI.getCurrent().setContent(new MainScreen((MyLifeUI)UI.getCurrent()));
					MainScreen hm = (MainScreen)UI.getCurrent().getSession().getAttribute("menu");
					hm.successfullLogin();
					//UI.getCurrent().getNavigator().navigateTo(OverviewView.VIEW_NAME);
					
				}else {
					Notification.show("Invalid Credentials");
				}
				
			}else {
				Notification.show("Invalid Credentials");
			}
			
		}
	 });
	  addComponent(loginButton);
	  
	  FormLayout registerContent = new FormLayout();
	  TextField registerName = new TextField(ui.getLabelBundle().getString("username"));
	  registerName.setIcon(VaadinIcons.USER);
	  registerName.setRequiredIndicatorVisible(true);
	  registerContent.addComponent(registerName);
	  
	  PasswordField passwordFieldRegister = new PasswordField(ui.getLabelBundle().getString("password"));
	  passwordFieldRegister.setIcon(VaadinIcons.STAR);
	  passwordFieldRegister.setRequiredIndicatorVisible(true);
	  registerContent.addComponent(passwordFieldRegister);
	  
	  PasswordField passwordFieldRegister2 = new PasswordField(ui.getLabelBundle().getString("password_repeat"));
	  passwordFieldRegister2.setIcon(VaadinIcons.STAR);
	  passwordFieldRegister2.setRequiredIndicatorVisible(true);
	  registerContent.addComponent(passwordFieldRegister2);
	  
	  Button registerButton = new Button(ui.getLabelBundle().getString("register"));
		 registerButton.addClickListener(new ClickListener() {
			 private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(passwordFieldRegister.getValue().equals(passwordFieldRegister2.getValue())) {
				
			  EntityManager	em = ui.getEntityManager(); 
			  User newUser = new User();
			  newUser.setLoginName(registerName.getValue());
			  Credentials credentials = new Credentials();
			  credentials.setForUser(newUser);
			  try {
			  String hashResult = PasswordHash.createHash(passwordFieldRegister.getValue());
			  
			  String[] parts = hashResult.split(":");
			  credentials.setIsActive(true);
			  credentials.setHashedPassword(hashResult);
			  newUser.setCredentials(credentials);
			  em.getTransaction().begin();
			  em.persist(newUser);
			  em.persist(credentials);
			  em.getTransaction().commit();
			  //em.close();
			  }
			  catch(Exception e) {
				  
			  }
				}else {
					Notification.show(ui.getMessageBundle().getString("password_missmatch"));
				}
			}
		 });
	  registerContent.addComponent(registerButton);
	  
	  //addComponent(new PopupView("Login", popupContent));
	  addComponent(new PopupView("Register", registerContent));
  }
}
