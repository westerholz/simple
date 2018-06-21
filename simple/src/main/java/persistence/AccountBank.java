package persistence;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.vaadin.icons.VaadinIcons;

@Entity
@Table(name = "accountbank")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountBank extends Account {

	@Override
	public String getAccountTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VaadinIcons getAccountTypeAsIcon() {
		
		return VaadinIcons.MONEY;
	}

}
