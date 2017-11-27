package jpaHibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class EntityWithSurrogateKey implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
