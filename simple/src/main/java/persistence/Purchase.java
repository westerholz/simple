package persistence;

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
import javax.persistence.Query;
import javax.persistence.Table;

import com.vaadin.ui.UI;

import UI.MyLifeUI;

@Entity
@Table(name = "pruchases")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Purchase extends PersistenceEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="accountItem")
	private AccountItem accountItem;
	@Column
	private Date purchaseDate;
	@Column
	private double purchasePrice;
	@Column
	private int purchaseAmount;
	Purchase(){
		
	}
	public Purchase(Account account, String wkn, Date purchaseDate, double price, int amount) {
		this.purchasePrice = price;
		this.purchaseDate = purchaseDate;
		this.purchaseAmount = amount;
		accountItem = account.getItemByWKN(wkn);
	}
}
