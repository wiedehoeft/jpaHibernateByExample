package jpaHibernate.service;

import jpaHibernate.model.Pilot;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class PersistenceService {

  private final EntityManagerFactory emf;
  private final JPAOperations jpaOperations;

  public PersistenceService() {
    emf = Persistence.createEntityManagerFactory("airport");
    jpaOperations = new JPAOperations();
  }

  public void save(final Pilot pilot) {

    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      entityManager.persist(pilot);
    });
  }

  public Pilot get(final long id) {

    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      return entityManager.find(Pilot.class, id);
    });
  }

  private EntityManagerFactory entityManagerFactory() {
    return emf;
  }

  public List<Pilot> getAllPilots() {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Query query = entityManager.createQuery("SELECT p FROM Pilot p");
      return (List<Pilot>) query.getResultList();
    });
  }

  public void delete(Pilot pilot) {
    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Pilot pilot1 = entityManager.merge(pilot);
      entityManager.remove(pilot1);
    });
  }

  public void update(Pilot pilot) {
    JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Pilot pilot1 = entityManager.merge(pilot);
      entityManager.persist(pilot1);
    });
  }
}
