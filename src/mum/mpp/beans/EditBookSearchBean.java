package mum.mpp.beans;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;

public class EditBookSearchBean {
	private SimpleStringProperty isbn;
	private SimpleStringProperty title;
	private SimpleIntegerProperty maxCheckoutDuration;
	private SimpleIntegerProperty numCopies;
	private List<Author> listAuthors;
	private List<BookCopy> listBookCopies;
	
	public EditBookSearchBean(String isbn, String title, int maxCheckoutDuration,
			List<Author> listAuthors, List<BookCopy> listBookCopies) {
		this.isbn = new SimpleStringProperty(isbn);
		this.title = new SimpleStringProperty(title);
		this.maxCheckoutDuration = new SimpleIntegerProperty(maxCheckoutDuration);
		this.listAuthors = listAuthors;
		this.listBookCopies = listBookCopies;
		
		if(listBookCopies!=null)
			this.numCopies=new SimpleIntegerProperty(listBookCopies.size());
	}
	
	public String getIsbn() {
		return isbn.get();
	}
	public void setIsbn(String isbn) {
		this.isbn = new SimpleStringProperty(isbn);
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}
	public int getMaxCheckoutDuration() {
		return maxCheckoutDuration.get();
	}
	public void setMaxCheckoutDuration(int maxCheckoutDuration) {
		this.maxCheckoutDuration = new SimpleIntegerProperty(maxCheckoutDuration);
	}
	public int getNumCopies() {
		return numCopies.get();
	}
	public void setNumCopies(int numCopies) {
		this.numCopies = new SimpleIntegerProperty(numCopies);
	}
	public List<Author> getListAuthors() {
		return listAuthors;
	}
	public void setListAuthors(List<Author> listAuthors) {
		this.listAuthors = listAuthors;
	}
	public List<BookCopy> getListBookCopies() {
		return listBookCopies;
	}
	public void setListBookCopies(List<BookCopy> listBookCopies) {
		this.listBookCopies = listBookCopies;
		if(listBookCopies!=null)
			this.numCopies=new SimpleIntegerProperty(listBookCopies.size());
	}

	@Override
	public String toString() {
		return "EditBookSearchBean [isbn=" + isbn + ", title=" + title + ", maxCheckoutDuration=" + maxCheckoutDuration
				+ ", numCopies=" + numCopies + ", listAuthors=" + listAuthors + ", listBookCopies=" + listBookCopies
				+ "]";
	}
	
	public Book getBook() {
		return new Book(getIsbn(),getTitle(),getListAuthors(),getMaxCheckoutDuration(),getListBookCopies());
	}
}
