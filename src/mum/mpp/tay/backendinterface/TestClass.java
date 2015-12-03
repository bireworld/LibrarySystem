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
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author 984761
 */
public class TestClass {

    // test admin imp
    public static void main(String[] args) throws ServiceException {

//        AllAccessIMP allAcc = (AllAccessIMP) InterfaceFactory.createAnInterface("101", "654321");
//
//        System.out.println(allAcc.getThisStaffObject());
//        if (true) {
//            return;
//        }
        AdminInterface adminI = (AdminInterface) InterfaceFactory.createAnInterface("10000", "123");

        adminI.addStaff(createAllAccessObject());

        Staff admin = adminI.addStaff(createAdminObject());
        System.out.println("Admin: " + admin);
        Staff librarian = adminI.addStaff(createLibrarianObject());
        Book book = adminI.addNewBook(createBookObject());
        BookCopy copy = new BookCopy(false, 5, book);
        adminI.addBookCopy(copy);
        Member member2 = adminI.addMember(createMemberObject());
        LibrarianInterface libI = (LibrarianInterface) InterfaceFactory.createAnInterface("" + librarian.getUniqueStaffId(), librarian.getPassword());
        Book b = libI.getBookByName("roomi");
        System.out.println("Book:" + b);
        Member member = libI.getMemberById(member2.getUniqueMemberNumber());
        System.out.println("Member: " + member);
        libI.checkout(b.getiSBNNumber(), member.getUniqueMemberNumber());
        List<CheckoutRecord> records = libI.getMemberRecord(member.getUniqueMemberNumber());
        for (CheckoutRecord rec : records) {
            System.out.println("Record :" + rec);
        }
        libI.checkIn(b.getiSBNNumber(), member.getUniqueMemberNumber());
    }

    /*
     public static void main2(String[] args) throws Exception {

     LibrarianInterface libI = new LibrarianIMP();
     List<Book> books = libI.getAllBooks();
     for (Book b : books) {
     System.out.println(b);
     }

     Book bookByName = libI.getBookByName("hafez");
     System.out.println("Book by name: " + bookByName);

     AdminInterface adminI = new AdminIMP();
     BookCopy copy = new BookCopy(false, 9, bookByName);
     try {
     //BookCopy copy2 = adminI.addBookCopy(copy);

     //System.out.println(copy2);
     } catch (Exception e) {
     e.printStackTrace();
     }

     try {
     //Librarian lib = adminI.addLibrarian(createLibrarianObject());
     //lib.setFirstName("Amirhossein 2");
     //adminI.editLibrarian(lib);
     //System.out.println(lib);
     } catch (Exception e) {
     e.printStackTrace();
     }

     boolean bookAvailable = libI.isBookAvailable("53y43");
     System.out.println("is book available: " + bookAvailable);

     try {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
     // search book
     BookJpaController bC = new BookJpaController(emf);
     Book b = bC.findByTitle("hafez");
     System.out.println(b);

     b.setMaximumCheckoutDurationInDays(41);
     b.setTitle("Hafez last poem");
     bC.edit(b);

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
     //
     //
     } catch (Exception ex) {
     Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     */
    public static Book createBookObject() {
        Book b = new Book("123ed32", "Roomi poems", null, 7, null);

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
        author2.setFirstName("Roomi");
        author2.setLastName("");
        author2.setPhoneNumber("9313211123");
        author2.setShortBiography("He was a true legend");

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

    public static Member createMemberObject() {

        Member admin = new Member();
        admin.setFirstName("Amir");
        admin.setLastName("Bahrami");
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        admin.setAddress(address);
        admin.setPhoneNumber("93132111121");;
        return admin;
    }

    public static Staff createAdminObject() {

        Staff admin = new Staff();
        admin.setFirstName("Amir");
        admin.setLastName("Bahrami");
        admin.setPassword("123456");
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

    public static Staff createLibrarianObject() {

        Staff admin = new Staff();
        admin.setFirstName("Amir");
        admin.setLastName("Bahrami");
        admin.setPassword("654321");
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

    public static Staff createAllAccessObject() {

        Staff admin = new Staff();
        admin.setFirstName("Maharishi");
        admin.setLastName("Bahrami");
        admin.setPassword("654321");
        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IA");
        address.setStreet("1000 N 4th");
        address.setZip("52557");
        admin.setAddress(address);
        admin.setPhoneNumber("93132111121");
        admin.setRole(AuthorizationLevel.FULLACCESS);
        return admin;
    }
}
