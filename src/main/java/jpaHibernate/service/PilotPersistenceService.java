package jpaHibernate.service;

import jpaHibernate.model.Pilot;

import javax.persistence.TypedQuery;
import java.util.List;

public class PilotPersistenceService extends PersistenceService<Pilot> {

  public Pilot get(final long id) {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      return entityManager.find(Pilot.class, id);
    });
  }

  public List<Pilot> getAll() {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      TypedQuery<Pilot> query = entityManager.createQuery("SELECT p FROM Pilot p", Pilot.class);
      return query.getResultList();
    });
  }
}
