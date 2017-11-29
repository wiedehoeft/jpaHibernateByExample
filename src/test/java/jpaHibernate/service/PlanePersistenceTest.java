package jpaHibernate.service;

import jpaHibernate.model.Airport;
import jpaHibernate.model.Pilot;
import jpaHibernate.model.Plane;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PlanePersistenceTest {

  @Test
  public void testCreatePlaneWithoutAirport() throws Exception {

    Airport airport = new Airport();
    Plane plane = new Plane();
    plane.setName("AE-541");
    plane.setAirport(airport);
    Pilot pilot = new Pilot();
    pilot.setFirstName("Daniel");
    pilot.setLastName("Düsentrieb");
    plane.setPilot(pilot);

    Assertions.assertThat(plane.getId()).isNotNull();

    new PlanePersistenceService().save(plane);

    Assertions.assertThat(plane.getId()).isNotNull();
  }
}
