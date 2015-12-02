/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author 984761
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "book.findByTitle",
            query = "SELECT b FROM Book b WHERE b.title LIKE :title"
    )
})
public class Book implements Serializable {

    public Book(String iSBNNumber, String title, List<Author> authors, int maximumCheckoutDurationInDays, List<BookCopy> copies) {
        this.iSBNNumber = iSBNNumber;
        this.title = title;
        this.authors = authors;
        this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
        this.copies = copies;
    }

    public Book() {
    }

    @Id
    private String iSBNNumber;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Author> authors;
    private int maximumCheckoutDurationInDays;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book")
    private List<BookCopy> copies;

    public String getiSBNNumber() {
        return iSBNNumber;
    }

    public void setiSBNNumber(String iSBNNumber) {
        this.iSBNNumber = iSBNNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getMaximumCheckoutDurationInDays() {
        return maximumCheckoutDurationInDays;
    }

    public void setMaximumCheckoutDurationInDays(int maximumCheckoutDurationInDays) {
        this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" + "iSBNNumber=" + iSBNNumber + ", title=" + title + 
                ", authors=" + authors + ", maximumCheckoutDurationInDays=" 
                + maximumCheckoutDurationInDays + ", copies=" + copies + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.iSBNNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.iSBNNumber, other.iSBNNumber)) {
            return false;
        }
        return true;
    }

}
