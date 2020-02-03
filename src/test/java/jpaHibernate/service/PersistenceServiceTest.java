package jpaHibernate.service;

import jpaHibernate.model.Pilot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersistenceServiceTest {

  private PilotPersistenceService persistenceService;
  private Pilot pilot1;
  private Pilot pilot2;

  @BeforeEach
  void setUp() {
    persistenceService = new PilotPersistenceService();
    System.out.println("currently pilot size" + persistenceService.getAll().size());
    initDatabaseWithPilots();
  }

  @Test
  public void testSavePilot() {

    // Assert
    assertThat(pilot1.getId()).isNotZero();
  }

  @Test
  public void testGetPilot() {

    // // Arrange
    long pilot2Id = pilot2.getId();

    // Act
    Pilot foundPilot = persistenceService.get(pilot2Id);

    // Assert
    assertThat(foundPilot).isNotNull();
  }

  @Test
  public void testGetAllPilots() {

    // Act
    List<Pilot> pilots = persistenceService.getAll();

    // Assert
    assertThat(pilots.size()).isEqualTo(2);
  }

  @Test
  public void testDeletePilot() {

    // Act
    persistenceService.delete(pilot2);

    // Assert
    List<Pilot> pilots = persistenceService.getAll();
    assertThat(pilots.size()).isEqualTo(1);
  }

  @Test
  public void testChangePilotsName() {
    // Arrange
    pilot2.setFirstName("Schantal");

    // Act
    persistenceService.update(pilot2);

    // Assert
    Pilot changed = persistenceService.get(pilot2.getId());
    assertThat(changed.getFirstName()).isEqualTo("Schantal");

  }

  private void initDatabaseWithPilots() {
    pilot1 = new Pilot();
    pilot1.setFirstName("Boris");
    pilot1.setLastName("Bruchpilot");
    pilot2 = new Pilot();
    pilot2.setFirstName("Schari");
    pilot2.setLastName("Schallmauer");
    System.out.println("currently pilot size" + persistenceService.getAll().size());

    persistenceService.save(pilot1);
    persistenceService.save(pilot2);
  }
}
