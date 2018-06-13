package persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import com.vaadin.ui.UI;

import UI.MyLifeUI;

@Entity
@NamedQuery(name="Account.getAll", query="Select a from Account a")
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account extends PersistenceEntity {
	@Column
	private String name;
	@Column
	private AccountType accountType;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shortID;

	public int getShortID() {
		return shortID;
	}

	public void setShortID(int shortID) {
		this.shortID = shortID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public Account() {
		
	}

	public Account(AccountType accountType) {
		this.accountType = accountType;
	}
	public static List<Account> getAllAccounts() {
		 EntityManager em = ((MyLifeUI)UI.getCurrent()).getEntityManager();
		
				 //Persistence.createEntityManagerFactory("simple").createEntityManager(((MyLifeUI)UI.getCurrent()).getDbMap());
		 
		 Query query = em.createNamedQuery("Account.getAll");

		 return query.getResultList();
		 
		 
		 // return	 em.createNamedQuery("User.getByLoginName", User.class).setParameter("loginName", loginName).getSingleResult();
		  
		}
}
