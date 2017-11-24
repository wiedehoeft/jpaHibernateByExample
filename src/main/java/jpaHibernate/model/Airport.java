package jpaHibernate.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Airport extends  EntityWithSurrogateKey {

  private String name;
  private String location;

  private List<Plane> planes;
}
