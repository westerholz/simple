package persistence;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersistenceEntity {

		  @Id
		  @GeneratedValue(generator = "UUID" )
		  @GenericGenerator(
				  name = "UUID",
				  strategy = "uuid2" )	  
		  @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "BINARY(16)" )
		  private UUID EntityID;
		  @ManyToOne(fetch=FetchType.LAZY)
		  @JoinColumn(name="assignedUser", referencedColumnName="ID")
		  private User assgnedToUser;
		  @Temporal(TemporalType.TIMESTAMP)
		  @CreationTimestamp
		  private Date createdAt;
		  @Temporal(TemporalType.TIMESTAMP)
		  @UpdateTimestamp
		  private Date lastChangedAt;
		  @ManyToOne(fetch=FetchType.LAZY)
		  @JoinColumn(name="createdBy")
		  private User createdBy;
		  @ManyToOne(fetch=FetchType.LAZY)
		  @JoinColumn(name="changedBy")
		  private User changedBy; 
		 // @PrePersist
		  //protected void onCreate() {
		   //creationDate = new Date();
		   //createdBy = "";
		 //}
	}

