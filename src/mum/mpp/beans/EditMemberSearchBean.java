package mum.mpp.beans;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;

public class EditMemberSearchBean {
	private SimpleLongProperty memberId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty phone;
	private SimpleStringProperty street;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty zip;
	private List<CheckoutRecord> checkoutRecList;
	
	public EditMemberSearchBean(long memberId, String firstName,
			String lastName, String phone, String street, String city,
			String state, String zip, List<CheckoutRecord> checkoutRecList) {
		this.memberId = new SimpleLongProperty(memberId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		this.checkoutRecList = checkoutRecList;
	}
	public long getMemberId() {
		return memberId.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getPhone() {
		return phone.get();
	}
	public String getStreet() {
		return street.get();
	}
	public String getCity() {
		return city.get();
	}
	public String getState() {
		return state.get();
	}
	public String getZip() {
		return zip.get();
	}
	public void setMemberId(long memberId) {
		this.memberId = new SimpleLongProperty(memberId);
	}
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	public void setStreet(String street) {
		this.street = new SimpleStringProperty(street);
	}
	public void setCity(String city) {
		this.city = new SimpleStringProperty(city);
	}
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	
	public void setZip(String zip) {
		this.zip = new SimpleStringProperty(zip);
	}
	
	public List<CheckoutRecord> getCheckoutRecList() {
		return checkoutRecList;
	}
	public void setCheckoutRecList(List<CheckoutRecord> checkoutRecList) {
		this.checkoutRecList = checkoutRecList;
	}
	public Member getMember() {
		Member temp = new Member();
		temp.setUniqueMemberNumber(this.getMemberId());
		temp.setFirstName(this.getFirstName());
		temp.setLastName(this.getLastName());
		temp.setPhoneNumber(this.getPhone());
		temp.setAddress(new Address(this.getStreet(), this.getCity(), 
				this.getState(), this.getZip()));
		temp.setRecords(this.getCheckoutRecList());
		return temp;
	}
	
	@Override
	public String toString() {
		return "EditMemberSearchBean [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ "]";
	}
}
