/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.BookCopyJpaController;
import mum.mpp.tay.controller.BookJpaController;
import mum.mpp.tay.controller.CheckoutRecordJpaController;
import mum.mpp.tay.controller.MemberJpaController;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author 984761
 */
public class LibrarianIMP implements LibrarianInterface {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
    private Staff staffObject;

    LibrarianIMP(Staff staff) {
        this.staffObject = staff;
    }

    @Override
    public Staff getThisStaffObject() {
        return staffObject;
    }

    @Override
    public boolean checkIn(String bookISBN, long memberId) throws ServiceException {
        try {
            MemberJpaController memberController = new MemberJpaController(emf);
            Member managedMember = memberController.findMember(memberId);
            BookJpaController bC = new BookJpaController(emf);
            Book book = bC.findBook(bookISBN);
            CheckoutRecord record = null;
            for (int i = managedMember.getRecords().size() - 1; i >= 0; i--) {
                if (managedMember.getRecords().get(i).getBook().getBook().equals(book) && managedMember.getRecords().get(i).getCheckinDate() == null) {
                    record = managedMember.getRecords().get(i);
                    break;
                }
            }

            // update record
            CheckoutRecordJpaController recordController = new CheckoutRecordJpaController(emf);
            record.getBook().setBorrowed(false);
            record.setCheckinDate(new Date());
            recordController.edit(record);

            return true;

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public CheckoutRecord checkout(String bookISBN, long memberId) throws ServiceException {
        try {
            boolean available = isBookAvailable(bookISBN);
            if (available) {
                BookJpaController bC = new BookJpaController(emf);
                Book book = bC.findBook(bookISBN);
                List<BookCopy> copies = book.getCopies();
                BookCopy copy = null;
                for (BookCopy cop : copies) {
                    if (!cop.isBorrowed()) {
                        copy = cop;
                        break;
                    }
                }

                MemberJpaController memberController = new MemberJpaController(emf);
                Member managedMember = memberController.findMember(memberId);

                long nowMils = System.currentTimeMillis();
                long dueMils = nowMils + (1000 * 60 * 60 * 24 * book.getMaximumCheckoutDurationInDays());

                CheckoutRecord record = new CheckoutRecord(copy, new Date(nowMils), new Date(dueMils), null, managedMember, null);
                CheckoutRecordJpaController checkController = new CheckoutRecordJpaController(emf);
                checkController.create(record);

                // member update
                managedMember.getRecords().add(record);
                memberController.edit(managedMember);

                // book copy update
                BookCopyJpaController copyController = new BookCopyJpaController(emf);
                copy.setBorrowed(true);
                copyController.edit(copy);

                return record;

            } else {
                throw new ServiceException("Book is not available");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean isBookAvailable(String ISBN) throws ServiceException {
        try {
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
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            BookJpaController bC = new BookJpaController(emf);
            return bC.findBookEntities();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Book getBookByName(String name) throws ServiceException {
        try {
            BookJpaController bC = new BookJpaController(emf);
            return bC.findByTitle(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Member getMemberById(long id) throws ServiceException {
        try {
            MemberJpaController mC = new MemberJpaController(emf);
            return mC.findMember(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<CheckoutRecord> getMemberRecord(long id) throws ServiceException {
        try {
            MemberJpaController mC = new MemberJpaController(emf);
            return mC.findMember(id).getRecords();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

}
