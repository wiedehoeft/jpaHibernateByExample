package jpaHibernate.service;

import jpaHibernate.model.Airport;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AirportPersistenceServiceTest {

  @Test
  public void testSaveAirport() throws Exception {
    Airport airport = new Airport();

    new AirportPersistenceService().save(airport);

    Assertions.assertThat(airport.getId()).isNotZero();
  }
}
