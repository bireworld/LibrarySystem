package mum.mpp.beans;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class EditAuthorSearchBean {
	private SimpleLongProperty authorId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty phone;
	private SimpleStringProperty street;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty zip;
	private SimpleStringProperty credentials;
	private SimpleStringProperty shortBio;
	
	
	public EditAuthorSearchBean(long authorId, String firstName, String lastName, String phone, String street,
			String city, String state, String zip, String credentials, String shortBio) {
		this.authorId = new SimpleLongProperty(authorId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
		this.credentials = new SimpleStringProperty(credentials);
		this.shortBio = new SimpleStringProperty(shortBio);
	}
	public long getAuthorId() {
		return authorId.get();
	}
	public void setAuthorId(long authorId) {
		this.authorId = new SimpleLongProperty(authorId);
	}
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	public String getStreet() {
		return street.get();
	}
	public void setStreet(String street) {
		this.street = new SimpleStringProperty(street);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city = new SimpleStringProperty(city);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	public String getZip() {
		return zip.get();
	}
	public void setZip(String zip) {
		this.zip = new SimpleStringProperty(zip);
	}
	public String getCredentials() {
		return credentials.get();
	}
	public void setCredentials(String credentials) {
		this.credentials = new SimpleStringProperty(credentials);
	}
	public String getShortBio() {
		return shortBio.get();
	}
	public void setShortBio(String shortBio) {
		this.shortBio = new SimpleStringProperty(shortBio);
	}
}
