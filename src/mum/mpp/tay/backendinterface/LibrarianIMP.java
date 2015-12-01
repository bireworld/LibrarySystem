/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.BookCopyJpaController;
import mum.mpp.tay.controller.BookJpaController;
import mum.mpp.tay.controller.MemberJpaController;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;

/**
 *
 * @author 984761
 */
public class LibrarianIMP implements LibrarianInterface {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");

    @Override
    public CheckoutRecord checkout(BookCopy copy, Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBookAvailable(String ISBN) {

        BookJpaController bC = new BookJpaController(emf);
        Book book = bC.findBook(ISBN);
        if (book == null || book.getCopies() == null || book.getCopies().isEmpty()) {
            return false;
        }
        List<BookCopy> copies = book.getCopies();
        for (BookCopy copy : copies) {
            if (!copy.isBorrowed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        BookJpaController bC = new BookJpaController(emf);
        return bC.findBookEntities();
    }

    @Override
    public Book getBookByName(String name) {
        BookJpaController bC = new BookJpaController(emf);
        return bC.findByTitle(name);
    }

    @Override
    public Member getMemberById(long id) {
        MemberJpaController mC = new MemberJpaController(emf);
        return mC.findMember(id);
    }

    @Override
    public List<CheckoutRecord> getMemberRecord(long id) {

        MemberJpaController mC = new MemberJpaController(emf);
        return mC.findMember(id).getRecords();

    }

    @Override
    public boolean checkIn(BookCopy copy, Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
