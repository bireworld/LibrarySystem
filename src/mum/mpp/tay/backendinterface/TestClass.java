/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.BookJpaController;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Admin;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Librarian;

/**
 *
 * @author 984761
 */
public class TestClass {

    // test admin imp
    public static void main(String[] args) throws ServiceException {
        AdminInterface adminI = new AdminIMP();

        Admin a = adminI.getAdmin(1);
        System.out.println(a);
        a.setFirstName("Amirhossein 2");
        adminI.editAdmin(a);
        System.out.println(a);

        Admin searchedAdmin = adminI.searchAdminByName("amirfg bahrafi");
        System.out.println("Searched admin: " + searchedAdmin);
    }

//    public static void main2(String[] args) {

//        String name = "";
//        String[] words = name.split(" ");
//        System.out.println("size:" + words.length);
//        for (String w : words) {
//            System.out.println("xx" + w);
//        }
//        String firstName = name.substring(0, name.indexOf(" ")).trim();
//        String lastName = name.substring(name.lastIndexOf(" ")).trim();
//        System.out.println(firstName + "--" + lastName);
//
//        if (true) {
//            return;
//        }
//
//        LibrarianInterface libI = new LibrarianIMP();
//        List<Book> books = libI.getAllBooks();
//        for (Book b : books) {
//            System.out.println(b);
//        }
//
//        Book bookByName = libI.getBookByName("hafez");
//        System.out.println("Book by name: " + bookByName);
//
//        AdminInterface adminI = new AdminIMP();
//        BookCopy copy = new BookCopy(false, 9, bookByName);
//        try {
//            //BookCopy copy2 = adminI.addBookCopy(copy);
//
//            //System.out.println(copy2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            //Librarian lib = adminI.addLibrarian(createLibrarianObject());
//            //lib.setFirstName("Amirhossein 2");
//            //adminI.editLibrarian(lib);
//            //System.out.println(lib);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        boolean bookAvailable = libI.isBookAvailable("53y43");
//        System.out.println("is book available: " + bookAvailable);
//
//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
//            // search book
//            BookJpaController bC = new BookJpaController(emf);
//            Book b = bC.findByTitle("hafez");
//            System.out.println(b);
//
//            b.setMaximumCheckoutDurationInDays(41);
//            b.setTitle("Hafez last poem");
//            bC.edit(b);
//
//            //AdminJpaController adminController = new AdminJpaController(emf);
//            //adminController.create(createAdminObject());
//            //
//            //
//            //
//            // create book
//            //BookJpaController bC = new BookJpaController(emf);
//            //bC.create(createBookObject());
//            //
//            //
//            //
//            //
//            //
//        } catch (Exception ex) {
//            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
    public static Book createBookObject() {
        Book b = new Book("53y43", "Hafez poems", null, 7, null);

        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        Author author1 = new Author();
        author1.setAddress(address);
        author1.setFirstName("Hafez");
        author1.setLastName("Shirazi");
        author1.setPhoneNumber("9313211122");
        author1.setShortBiography("He was a real legend");

        Author author2 = new Author();
        author2.setAddress(address);
        author2.setFirstName("Ferdowsi");
        author2.setLastName("");
        author2.setPhoneNumber("9313211123");
        author2.setShortBiography("He was a true legend");

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        b.setAuthors(authors);

        BookCopy copy1 = new BookCopy(false, 5, b);
        BookCopy copy2 = new BookCopy(false, 6, b);
        BookCopy copy3 = new BookCopy(false, 7, b);
        BookCopy copy4 = new BookCopy(false, 8, b);

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

    public static Librarian createLibrarianObject() {

        Librarian admin = new Librarian();
        admin.setFirstName("Amir");
        admin.setLastName("Bahrami");
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        admin.setAddress(address);
        admin.setPhoneNumber("93132111121");
        admin.setRole(AuthorizationLevel.LIBRARIAN);
        return admin;
    }
}
