package persistence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.vaadin.icons.VaadinIcons;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountDepot extends Account {
	@OneToMany(fetch = FetchType.LAZY)
	private Map<String,AccountDepotItem> items;
	@Override
	public String getAccountTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VaadinIcons getAccountTypeAsIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	public AccountDepotItem getItemByWKN(String wkn) {
		return items.get(wkn);
	}

}
