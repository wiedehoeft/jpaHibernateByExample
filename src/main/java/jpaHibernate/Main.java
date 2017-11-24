package jpaHibernate;

import jpaHibernate.model.Pilot;
import jpaHibernate.service.PersistenceService;

public class Main {

  public static void main(String[] args) {
    PersistenceService persistenceService = new PersistenceService();

    persistenceService.save(new Pilot());
  }
}
