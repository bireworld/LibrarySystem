/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;

/**
 *
 * @author 984761
 */
public interface LibrarianInterface {

    CheckoutRecord checkout(BookCopy copy, Member member);

    boolean isBookAvailable(String ISBN);

    List<Book> getAllBooks();

    Book getBookByName(String name);

    Member getMemberById(long id);

    List<CheckoutRecord> getMemberRecord(long memberId);

    boolean checkIn(BookCopy copy, Member member);

}
