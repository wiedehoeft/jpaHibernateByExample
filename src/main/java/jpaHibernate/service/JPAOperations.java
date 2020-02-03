package jpaHibernate.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Sources, see: https://github.com/hibernate/hibernate-orm/blob/master/hibernate-testing/src/main/java/org/hibernate/testing/transaction/TransactionUtil.java
 */
public class JPAOperations {

  /**
   * JPA transaction function
   *
   * @param <T> function result
   */
  @FunctionalInterface
  public interface JPATransactionFunction<T> extends Function<EntityManager, T> {

    default void beforeTransactionCompletion() {

    }

    default void afterTransactionCompletion() {

    }
  }

  /**
   * JPA transaction function without return value
   */
  @FunctionalInterface
  public interface JPATransactionVoidFunction extends Consumer<EntityManager> {
    default void beforeTransactionCompletion() {

    }

    default void afterTransactionCompletion() {

    }
  }


  public static <T> T doInJPA(
    Supplier<EntityManagerFactory> factorySupplier,
    JPATransactionFunction<T> function) {
    T result = null;
    EntityManager entityManager = null;
    EntityTransaction txn = null;
    try {
      entityManager =
        factorySupplier.get().createEntityManager();
      function.beforeTransactionCompletion();
      txn = entityManager.getTransaction();
      txn.begin();
      result = function.apply(entityManager);
      txn.commit();
    } catch (Throwable e) {
      if (txn != null && txn.isActive()) {
        txn.rollback();
      }
      throw e;
    } finally {
      function.afterTransactionCompletion();
      if (entityManager != null) {
        entityManager.close();
      }
    }
    return result;
  }

  public static void doInJPA(
    Supplier<EntityManagerFactory> factorySupplier,
    JPATransactionVoidFunction function) {
    EntityManager entityManager = null;
    EntityTransaction txn = null;
    try {
      entityManager =
        factorySupplier.get().createEntityManager();
      function.beforeTransactionCompletion();
      txn = entityManager.getTransaction();
      txn.begin();
      function.accept(entityManager);
      if (!txn.getRollbackOnly()) {
        txn.commit();
      } else {
        txn.rollback();
      }
    } catch (Throwable t) {
      if (txn != null && txn.isActive()) {
        txn.rollback();
      }
      throw t;
    } finally {
      function.afterTransactionCompletion();
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }
}
