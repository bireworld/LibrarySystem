/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 984761
 */
@Entity
public class CheckoutRecord implements Serializable {

    public CheckoutRecord(BookCopy book, Date checkoutDate, Date dueDate, Date checkinDate, Member member, Fine fine) {
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.checkinDate = checkinDate;
        this.member = member;
        this.fine = fine;
    }

    public CheckoutRecord() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private BookCopy book;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkinDate;
    @ManyToOne
    private Member member;
    @OneToOne(mappedBy = "record", orphanRemoval = true)
    private Fine fine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookCopy getBook() {
        return book;
    }

    public void setBook(BookCopy book) {
        this.book = book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Fine getFine() {
        return fine;
    }

    public void setFine(Fine fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "CheckoutRecord{" + "id=" + id + ", book=" + book + ", checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", checkinDate=" + checkinDate + ", member=" + member + ", fine=" + fine + '}';
    }

}
