package jpaHibernate.service;

import jpaHibernate.model.EntityWithSurrogateKey;
import jpaHibernate.model.Pilot;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class PersistenceService<E extends EntityWithSurrogateKey> {

  private final EntityManagerFactory emf;

  public PersistenceService() {
    emf = Persistence.createEntityManagerFactory("airport");
  }

  EntityManagerFactory entityManagerFactory() {
    return emf;
  }

  public void save(final E entity) {
    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      entityManager.persist(entity);
    });
  }

  public void delete(final E entity) {
    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      E merged = entityManager.merge(entity);
      entityManager.remove(merged);
    });
  }

  public void update(final E entity) {
    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      E merged = entityManager.merge(entity);
      entityManager.persist(merged);
    });
  }
}
