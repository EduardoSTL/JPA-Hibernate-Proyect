package org.cursosprimgboot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.cursosprimgboot.entity.Cliente;
import org.cursosprimgboot.util.JpaUtil;


import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese el id: ");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);
        Long id2 = s.nextLong();
        Cliente cliente2 = em.find(Cliente.class, id2);
        System.out.println(cliente2);

        em.close();
    }
}
