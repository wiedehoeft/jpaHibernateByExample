package jpaHibernate.model;

import javax.persistence.Entity;

@Entity
public class Plane extends EntityWithSurrogateKey {

  private String name;
  private int capacity;

  private Pilot pilot;
}
