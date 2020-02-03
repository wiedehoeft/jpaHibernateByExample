package jpaHibernate.service.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MySQLContainer;

public class PersistenceServiceIntegrationTest {

  public static MySQLContainer mysql = new MySQLContainer();

  @BeforeAll
  public static void beforeClass() {
    mysql.start();
  }

  @AfterAll
  public static void afterClass() {
    mysql.stop();
  }

}
