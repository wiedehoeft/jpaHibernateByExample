package jpaHibernate.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityWithSurrogateKey {

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
