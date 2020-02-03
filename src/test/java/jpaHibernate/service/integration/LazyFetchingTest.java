package jpaHibernate.service.integration;

import jpaHibernate.model.Airport;
import jpaHibernate.model.Pilot;
import jpaHibernate.model.Plane;
import jpaHibernate.service.AirportPersistenceService;
import jpaHibernate.service.PilotPersistenceService;
import jpaHibernate.service.PlanePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LazyFetchingTest {

  private AirportPersistenceService sut;
  private PlanePersistenceService planePersistenceService;
  private PilotPersistenceService pilotPersistenceService;

  @BeforeEach
  void setUp() {
    sut = new AirportPersistenceService();
    planePersistenceService = new PlanePersistenceService();
    pilotPersistenceService = new PilotPersistenceService();
  }

  @Test
  void fetchPlanesFromAirport() {
    // Arrange
    final Pilot pilot = new Pilot();
    pilot.setFirstName("Hugo");
    pilot.setLastName("Egon");
    pilot.setAge(55);

    final Pilot pilot2 = new Pilot();
    pilot2.setFirstName("Lisa");
    pilot2.setLastName("Egon");
    pilot2.setAge(55);

    final Plane plane = new Plane();
    plane.setCapacity(100);
    plane.setPilot(pilot);

    final Plane plane2 = new Plane();
    plane2.setCapacity(200);
    plane2.setPilot(pilot2);

    final Airport anAirport = new Airport();
    anAirport.setLocation("anIsland");
    anAirport.setName("FerryIsland");
    anAirport.addPlane(plane);
    anAirport.addPlane(plane2);
    sut.save(anAirport);

    // Act
    final List<Airport> airport = sut.findAll();
    final List<Airport> airportWithAllInformation = airport.get(0).fetchInformation();

    final List<Plane> planes = planePersistenceService.findAll();

    final Plane singlePlane = planePersistenceService.get(1);

    final List<Plane> planesByAirport = airport.get(0).fetchInformation().get(0).getPlanes();

    // Assert
    System.out.println("Planes");
    planesByAirport.forEach(System.out::println);
  }
}
