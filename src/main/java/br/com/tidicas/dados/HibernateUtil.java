package br.com.tidicas.dados;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

  private static final EntityManagerFactory entityManagerFactory;
  static {
    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("hbPU");
      System.out.println("Entity Menager Test.............." + entityManagerFactory);
    } catch (Throwable ex) {

      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);

    }
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }

}