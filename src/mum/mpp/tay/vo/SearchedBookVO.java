package mum.mpp.tay.vo;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;

public class SearchedBookVO {
    private StringProperty iSBNNumber;
    private StringProperty title;
    private StringProperty authors;
    private IntegerProperty maximumCheckoutDurationInDays;
    private SimpleLongProperty copyNumber;
}
