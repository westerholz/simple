package persistence;

import java.sql.Blob;

import javax.persistence.Entity;
@Entity
public class Attachment extends PersistenceEntity {
private Blob attachment;
}
