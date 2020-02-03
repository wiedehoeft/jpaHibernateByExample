package jpaHibernate.service;

import jpaHibernate.model.Airport;
import jpaHibernate.model.Pilot;
import jpaHibernate.model.Plane;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanePersistenceTest {

  @Test
  public void testCreatePlaneWithoutAirport() {

    // Arrange
    Airport airport = new Airport();
    Plane plane = new Plane();
    plane.setName("AE-541");
    plane.setAirport(airport);
    Pilot pilot = new Pilot();
    pilot.setFirstName("Daniel");
    pilot.setLastName("Düsentrieb");
    plane.setPilot(pilot);

    Assertions.assertThat(plane.getId()).isNotNull();

    // Act
    new PlanePersistenceService().save(plane);

    // Assert
    Assertions.assertThat(plane.getId()).isNotNull();
  }
}
