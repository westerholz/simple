package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Credentials")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Credentials extends PersistenceEntity {
	@OneToOne(mappedBy="credentials", targetEntity=User.class, fetch=FetchType.EAGER )
	private User forUser;
	@Column(name = "password")
	private String hashedPassword;
	@Column(name = "isActive")
	private Boolean isActive;
	public User getForUser() {
		return forUser;
	}
	public void setForUser(User forUser) {
		this.forUser = forUser;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
