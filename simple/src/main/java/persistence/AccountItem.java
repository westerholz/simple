package persistence;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import com.vaadin.ui.UI;

import UI.MyLifeUI;

@Entity
@NamedQuery(name="AccountItem.getAllForAccount", query="Select a from AccountItem a where a.account = :Account")
@Table(name = "accountitem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AccountItem extends PersistenceEntity {
	@Column
	private String shortID;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account")
	private Account account;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Purchase> purchases;
	
	@Column
	private String wkn;
	
	
	
	@Column
	private Currency currency;
	
	
	
	

	
		  
		
}
