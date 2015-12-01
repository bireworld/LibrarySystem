/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Admin;
import mum.mpp.tay.entity.AuthorizationLevel;

/**
 *
 * @author 984761
 */
public class TestClass {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
        EntityManager em = emf.createEntityManager();

        AdminJpaController adminController = new AdminJpaController(emf);
        Admin admin = new Admin();
        admin.setFirstName("Amir");
        admin.setLastName("Bahrami");
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        admin.setAddress(address);
        admin.setPhoneNumber("93132111121");
        admin.setRole(AuthorizationLevel.ADMIN);

        adminController.create(admin);

    }

}
