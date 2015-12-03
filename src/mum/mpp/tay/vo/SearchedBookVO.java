package mum.mpp.tay.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;

public class SearchedBookVO {
	private StringProperty iSBNNumber;
	private StringProperty title;
	private StringProperty authors;
	private IntegerProperty maximumCheckoutDurationInDays;
	private SimpleLongProperty copyNumber;

	public SearchedBookVO(StringProperty iSBNNumber, StringProperty title, StringProperty authors,
			IntegerProperty maximumCheckoutDurationInDays, SimpleLongProperty copyNumber) {
		super();
		this.iSBNNumber = iSBNNumber;
		this.title = title;
		this.authors = authors;
		this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
		this.copyNumber = copyNumber;
	}

	public StringProperty getiSBNNumber() {
		return iSBNNumber;
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

	public StringProperty getAuthors() {
		return authors;
	}

	public void setAuthors(StringProperty authors) {
		this.authors = authors;
	}

	public IntegerProperty getMaximumCheckoutDurationInDays() {
		return maximumCheckoutDurationInDays;
	}

	public void setMaximumCheckoutDurationInDays(IntegerProperty maximumCheckoutDurationInDays) {
		this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
	}

	public SimpleLongProperty getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(SimpleLongProperty copyNumber) {
		this.copyNumber = copyNumber;
	}

}
