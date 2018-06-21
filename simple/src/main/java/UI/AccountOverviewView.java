package UI;

import javax.persistence.EntityManager;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import persistence.Account;
import persistence.AccountBank;
import persistence.AccountDepot;



public class AccountOverviewView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		/*em.getTransaction().begin();
		AccountBank ab = new AccountBank();
		ab.setShortID("123");
		em.persist(ab);
		AccountDepot ad = new AccountDepot();
		ad.setShortID("123");
		em.persist(ad);
		em.getTransaction().commit();*/
		//accountGrid.setSizeFull();
		//accountGrid.addStyleName("Kai");
		accountGrid.setItems(Account.getAllAccounts());
		accountGrid.addColumn(Account::getAccountTypeAsIcon).setCaption(ui.getLabelBundle().getString("accounttype"));
		accountGrid.addColumn(Account::getShortID).setCaption(ui.getLabelBundle().getString("accountid"));
		Column<Account, String> accountGridNameColumn = accountGrid.addColumn(Account::getName);
		accountGridNameColumn.setCaption(ui.getLabelBundle().getString("accountname"));
		
		Binder<Account> binder  = accountGrid.getEditor().getBinder();
		TextField accountNameField = new TextField();
		Binding<Account, String> accountNameBinding = binder.bind(accountNameField,Account::getAccountTypeName,Account::setName);
		accountGridNameColumn.setEditorBinding(accountNameBinding);
		accountGridNameColumn.setEditable(true);
		accountGrid.getEditor().setEnabled(true);
		
		GridCrud<Account> crudGrid = new GridCrud<>(Account.class);
		
    
    layout.addComponent(accountGrid);
    layout.addComponent(crudGrid);
    addComponent(layout);
	}
    
}

