package jpaHibernate.model;

import javax.persistence.Entity;

@Entity
public class Pilot extends  EntityWithSurrogateKey {

  private String name;
  private int age;
}
