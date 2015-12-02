package mum.mpp.beans;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class EditAdminSearchBean {
	private SimpleLongProperty adminId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty phone;
	private SimpleStringProperty street;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty zip;
	
	public EditAdminSearchBean(long adminId, String firstName,
			String lastName, String phone, String street, String city,
			String state, String zip) {
		this.adminId = new SimpleLongProperty(adminId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
	}
	public long getAdminId() {
		return adminId.get();
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
	public void setStaffId(long adminId) {
		this.adminId = new SimpleLongProperty(adminId);
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
}
