package jpaHibernate.service;

import jpaHibernate.model.Pilot;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceServiceTest {

  private PilotPersistenceService persistenceService;
  private Pilot pilot1;
  private Pilot pilot2;

  @Before
  public void setUp() throws Exception {
    persistenceService = new PilotPersistenceService();
    initDatabaseWithPilots();
  }

  @Test
  public void testSavePilot() throws Exception {

    // Then
    assertThat(pilot1.getId()).isNotZero();
    //assertThat(pilot2.getEintritt()).isNotNull();
  }

  @Test
  public void testGetPilot() throws Exception {

    // Given
    long pilot2Id = pilot2.getId();

    // When
    Pilot foundPilot = persistenceService.get(pilot2Id);

    // Then
    assertThat(foundPilot).isNotNull();
  }

  @Test
  public void testGetAllPilots() {

    // When
    List<Pilot> pilots = persistenceService.getAll();

    // Then
    assertThat(pilots.size()).isEqualTo(2);
  }

  @Test
  public void testDeletePilot() {

    // When
    persistenceService.delete(pilot2);

    // Then
    List<Pilot> pilots = persistenceService.getAll();
    assertThat(pilots.size()).isEqualTo(1);
  }

  @Test
  public void testChangePilotsName() throws Exception {

    pilot2.setFirstName("Schantal");

    persistenceService.update(pilot2);

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
    persistenceService.save(pilot1);
    persistenceService.save(pilot2);
  }
}