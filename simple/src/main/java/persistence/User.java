package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.NamedQuery;

import com.vaadin.ui.UI;

import UI.MyLifeUI;

@Entity
@Table(name = "User")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@NamedQuery(name="User.getByLoginName", query="Select u from User u where u.loginName = :loginName")
public class User extends PersistenceEntity {
	@PersistenceContext(unitName="simple")
	private static EntityManager em;
	@Column(name = "loginName", unique=true)
	private String loginName;
	@OneToOne(targetEntity=Credentials.class, fetch=FetchType.EAGER )
	@JoinColumn(name="credentials",  referencedColumnName="ID")
	private Credentials credentials;



	@Column(name = "username")
	private String userName;
	@Column(name = "email")
	private String EmailAddress;
	
	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public Credentials getCredentials() {
		return credentials;
	}


	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public static User getUserByLoginName(String loginName) {
	 em = ((MyLifeUI)UI.getCurrent()).getEntityManager();

	 TypedQuery<User> query = em.createNamedQuery("User.getByLoginName", User.class);
	 query.setParameter("loginName", loginName);
	 return query.getSingleResult();

	  
	}
}
