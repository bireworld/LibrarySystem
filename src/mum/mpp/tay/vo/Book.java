package mum.mpp.tay.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private StringProperty iSBNNumber;
    private StringProperty title;
//    private List<Author> authors;
    private IntegerProperty maximumCheckoutDurationInDays;
//    private List<BookCopy> copies;
    
	public StringProperty getiSBNNumber() {
		return iSBNNumber;
	}
	public Book(String iSBNNumber, String title, Integer maximumCheckoutDurationInDays) {
		super();
		this.iSBNNumber = new SimpleStringProperty(iSBNNumber);
		this.title = new SimpleStringProperty(title);
		this.maximumCheckoutDurationInDays = new SimpleIntegerProperty(maximumCheckoutDurationInDays);
	}
	public void setiSBNNumber(StringProperty iSBNNumber) {
		this.iSBNNumber = iSBNNumber;
	}
	public StringProperty getTitle() {
		return title;
	}
	public void setTitle(StringProperty title) {
		this.title = title;
	}
	public IntegerProperty getMaximumCheckoutDurationInDays() {
		return maximumCheckoutDurationInDays;
	}
	public void setMaximumCheckoutDurationInDays(IntegerProperty maximumCheckoutDurationInDays) {
		this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
	}

    
}
