package jpaHibernate.service.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;

public class PersistenceServiceIntegrationTest {
  public static MySQLContainer mysql = new MySQLContainer();

  @BeforeClass
  public static void beforeClass() {
    mysql.start();
  }

  @AfterClass
  public static void afterClass() {
    mysql.stop();
  }

}
