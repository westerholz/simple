package UI;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import persistence.Account;
import persistence.AccountType;



public class AccountOverviewView extends VerticalLayout implements View {
	public static final String viewName = "Accounts";
	private MyLifeUI ui;
	private EntityManager em;
	public static final String VIEW_NAME = "Overview";
	private Grid<Account> accountGrid;
	public AccountOverviewView() {
		VerticalLayout layout = new VerticalLayout();
		setSizeFull();
		ui = (MyLifeUI)UI.getCurrent();
		em = ui.getEntityManager();
		accountGrid = new Grid<Account>();
		em.getTransaction().begin();
		//em.persist(new Document());
		//em.persist(new Account(AccountType.ASSET_ACTIVE));
		em.getTransaction().commit();
		accountGrid.setSizeFull();
		accountGrid.addStyleName("Kai");
		accountGrid.setItems(Account.getAllAccounts());
		accountGrid.addColumn(Account::getAccountType).setCaption("AccountType");
		accountGrid.setStyleGenerator(cellRef -> { return "kai";});

    
    layout.addComponent(accountGrid);
    addComponent(layout);
	}
    
}

