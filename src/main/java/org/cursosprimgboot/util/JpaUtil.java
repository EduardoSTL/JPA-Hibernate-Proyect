package org.cursosprimgboot.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory =
            buildEntityManagerFactory();

    //Interface con el objetivo de comunicar con la base de datos y manejar las tablas
    //consulta con el puerto de la base de datos(en este caso el de mysql)
    private static EntityManagerFactory buildEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("ejemploJPA");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
