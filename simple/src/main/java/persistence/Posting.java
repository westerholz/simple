package persistence;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Table(name = "posting")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Posting extends PersistenceEntity{

}
