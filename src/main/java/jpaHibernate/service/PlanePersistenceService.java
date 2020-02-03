package jpaHibernate.service;

import jpaHibernate.model.Plane;

import javax.persistence.Query;
import java.util.List;

public class PlanePersistenceService extends PersistenceService<Plane> {

  public Plane get(final long id) {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      return entityManager.find(Plane.class, id);
    });
  }

  public List<Plane> findAll() {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Query query = entityManager.createQuery("SELECT p FROM Plane p");
      return (List<Plane>) query.getResultList();
    });
  }
}
