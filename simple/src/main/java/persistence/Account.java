package persistence;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Account extends PersistenceEntity {
  private AccountType accountType;
  
  public Account(AccountType accountType) {
	  this.accountType = accountType;
  }
}
