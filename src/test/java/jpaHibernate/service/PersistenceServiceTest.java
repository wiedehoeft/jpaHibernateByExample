package jpaHibernate.service;

import jpaHibernate.model.Pilot;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceServiceTest {

  private PersistenceService persistenceService;
  private Pilot pilot1;
  private Pilot pilot2;

  @Before
  public void setUp() throws Exception {
    persistenceService = new PersistenceService();
    initDatabaseWithPilots();
  }

  @Test
  public void testSavePilot() throws Exception {

    // Given
    Pilot pilot = new Pilot();

    // When
    persistenceService.save(pilot);

    // Then
    assertThat(pilot.getId()).isNotZero();
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
    List<Pilot> pilots = persistenceService.getAllPilots();

    // Then
    assertThat(pilots.size()).isEqualTo(2);
  }

  @Test
  public void testDeletePilot() {

    // When
    persistenceService.delete(pilot2);

    // Then
    List<Pilot> pilots = persistenceService.getAllPilots();
    assertThat(pilots.size()).isEqualTo(1);
  }

  private void initDatabaseWithPilots() {
    pilot1 = new Pilot();
    pilot2 = new Pilot();
    persistenceService.save(pilot1);
    persistenceService.save(pilot2);
  }
}