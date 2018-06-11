package persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asset")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Asset extends PersistenceEntity{
	@ManyToOne(targetEntity=Account.class, fetch=FetchType.EAGER )
	@JoinColumn(name="assignedAccount",  referencedColumnName="ID")
  private Account assignedAccount;
}
