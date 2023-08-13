package org.cursosprimgboot;

import jakarta.persistence.EntityManager;
import org.cursosprimgboot.entity.Cliente;
import org.cursosprimgboot.util.JpaUtil;

import java.util.List;

public class HibernateHQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("----- Start HibernateHQL -----");
        System.out.println("========== consultar todos ==========");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta por ID ==========");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L).getSingleResult();
        System.out.println(cliente);

        System.out.println("========== consulta solo el nombre por el ID ==========");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                .setParameter("id", 2L).getSingleResult();
        System.out.println(nombreCliente);
    }
}
