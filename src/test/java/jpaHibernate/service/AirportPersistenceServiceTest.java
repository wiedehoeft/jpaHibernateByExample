package jpaHibernate.service;

import jpaHibernate.model.Airport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AirportPersistenceServiceTest {

  @Test
  void testSaveAirport() {
    Airport airport = new Airport();

    new AirportPersistenceService().save(airport);

    Assertions.assertThat(airport.getId()).isNotZero();
  }
}
