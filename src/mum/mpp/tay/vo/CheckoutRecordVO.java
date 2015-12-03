package mum.mpp.tay.vo;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckoutRecordVO {
	private long id;
    private StringProperty iSBNNumber;
    private StringProperty title;
    private IntegerProperty maximumCheckoutDurationInDays;
    private SimpleLongProperty copyNumber;
    private ObjectProperty<Date> checkoutDate;
    private ObjectProperty<Date> dueDate;
    private ObjectProperty<Date> checkinDate;
    
	public StringProperty getiSBNNumber() {
		return iSBNNumber;
	}
	public CheckoutRecordVO(String iSBNNumber, String title, Integer maximumCheckoutDurationInDays) {
		super();
		this.iSBNNumber = new SimpleStringProperty(iSBNNumber);
		this.title = new SimpleStringProperty(title);
		this.maximumCheckoutDurationInDays = new SimpleIntegerProperty(maximumCheckoutDurationInDays);
	}
	
	public CheckoutRecordVO(Long id, String iSBNNumber, String title, Integer maximumCheckoutDurationInDays,
			Long copyNumber, Date checkoutDate, Date dueDate, Date checkinDate) {
		super();
		this.id = id;
		this.iSBNNumber = new SimpleStringProperty(iSBNNumber);
		this.title = new SimpleStringProperty(title);
		this.maximumCheckoutDurationInDays = new SimpleIntegerProperty(maximumCheckoutDurationInDays);
		this.copyNumber = new SimpleLongProperty(copyNumber);
		this.checkoutDate = new SimpleObjectProperty<Date>(checkoutDate);
		this.dueDate = new SimpleObjectProperty<Date>(dueDate);
		this.checkinDate = new SimpleObjectProperty<Date>(checkinDate);
//		this.dueDate = dueDate;
//		this.checkinDate = checkinDate;
	}

	public StringProperty getTitle() {
		return title;
	}

	public IntegerProperty getMaximumCheckoutDurationInDays() {
		return maximumCheckoutDurationInDays;
	}

	public SimpleLongProperty getCopyNumber() {
		return copyNumber;
	}
	
	public ObjectProperty getCheckoutDate() {
		return checkoutDate;
	}
	public ObjectProperty getDueDate() {
		return dueDate;
	}
	public ObjectProperty getCheckinDate() {
		return checkinDate;
	}
	public long getId() {
		return id;
	}

    
}
