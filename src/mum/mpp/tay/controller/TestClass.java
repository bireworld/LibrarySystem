/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Admin;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;

/**
 *
 * @author 984761
 */
public class TestClass {

    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");

            //AdminJpaController adminController = new AdminJpaController(emf);
            //adminController.create(createAdminObject());
            //
            //
            //
            // create book
            //BookJpaController bC = new BookJpaController(emf);
            //bC.create(createBookObject());
            //
            //
            //
            // search book
            //BookJpaController bC = new BookJpaController(emf);
            //Book b = bC.findByTitle("omi poem");
            //System.out.println(b);
            //
            //
        } catch (Exception ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Book createBookObject() {
        Book b = new Book("34jduy3", "Roomi poems", null, 21, null);

        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        Author author1 = new Author();
        author1.setAddress(address);
        author1.setFirstName("Shams");
        author1.setLastName("Tabrizi");
        author1.setPhoneNumber("9313211122");
        author1.setShortBiography("He was a legend");

        Author author2 = new Author();
        author2.setAddress(address);
        author2.setFirstName("Saadi");
        author2.setLastName("Shirazi");
        author2.setPhoneNumber("9313211123");
        author2.setShortBiography("He was a legend too");

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        b.setAuthors(authors);

        BookCopy copy1 = new BookCopy(false, 1, b);
        BookCopy copy2 = new BookCopy(false, 2, b);
        BookCopy copy3 = new BookCopy(false, 3, b);
        BookCopy copy4 = new BookCopy(false, 4, b);

        List<BookCopy> copies = new ArrayList<>();
        copies.add(copy1);
        copies.add(copy2);
        copies.add(copy3);
        copies.add(copy4);

        b.setCopies(copies);

        return b;
    }

    public static Admin createAdminObject() {

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
        return admin;
    }
}
