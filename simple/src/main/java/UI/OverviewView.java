package UI;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;



public class OverviewView extends CssLayout implements View {
	public static final String viewName = "Overview View";
	private EntityManager em;
	public static final String VIEW_NAME = "Overview";
	public OverviewView() {
		setSizeFull();
		em = ((MyLifeUI)UI.getCurrent()).getEntityManager();
   
		em.getTransaction().begin();
		//em.persist(new Document());
		em.getTransaction().commit();
    final VerticalLayout layout = new VerticalLayout();
    final Label label = new Label("This is your Overview");
    label.addStyleName(ValoTheme.LABEL_H1);
    
    layout.addComponent(label);
    addComponent(layout);
	}
    
}

