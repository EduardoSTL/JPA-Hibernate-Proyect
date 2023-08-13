package org.cursosprimgboot;

import jakarta.persistence.EntityManager;
import org.cursosprimgboot.dominio.ClienteDto;
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

        System.out.println("========== consulta por campos personalizados ===========");
        Object[] objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id=" + id + ",nombre=" + nombre + ",apellido=" + apellido);

        System.out.println("========== consulta por campos personalizados lista ===========");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        //for (Object[] reg : registros) {
        registros.forEach( reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id=" + idCli + ",nombre=" + nombreCli + ",apellido=" + apellidoCli);
        });

        System.out.println("=========== consulta por cliente y forma de pago ==========");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente)reg[0];
            String formaPago = (String) reg[1];
            System.out.println("formaPago=" + formaPago + "," + c);
        });

        System.out.println("========== consulta que puebla y devuelve objeto entity de una clase personalizada ==========");
        clientes = em.createQuery("select new Cliente (c.nombre, c.apellido) from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta que puebla y devuelve objeto otro de una clase personalizada ==========");
        List<ClienteDto> clientesDto = em.createQuery("select new org.cursosprimgboot.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                .getResultList();
        clientesDto.forEach(System.out::println);

        System.out.println("========== consulta con nombres de clientes ===========");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);
    }
}