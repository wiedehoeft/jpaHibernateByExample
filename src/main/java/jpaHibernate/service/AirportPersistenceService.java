package jpaHibernate.service;

import jpaHibernate.model.Airport;
import jpaHibernate.model.Plane;

import javax.persistence.Query;
import java.util.List;

public class AirportPersistenceService extends PersistenceService<Airport> {

  public List<Airport> fetchPlanes() {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Query query = entityManager.createQuery("select ap from Airport ap join ap.planes pl where pl.capacity > 100");
      return query.getResultList();
    });
  }

  public List<Airport> findAll() {
    return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
      Query query = entityManager.createQuery("select ap from Airport ap");
      return query.getResultList();
    });
  }
}
