package mum.mpp.views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Librarian;
import mum.mpp.utils.AuthUtils;
import mum.mpp.utils.ValidationUtils;

public class AddLibrarianController {
	@FXML
	private Label lblErrorMsgs;
	
	@FXML
	private TextField txtfFirstName;
	
	@FXML
	private TextField txtfLastName;
	
	@FXML
	private TextField txtfStreet;
	
	@FXML
	private TextField txtfCity;
	
	@FXML
	private TextField txtfState;
	
	@FXML
	private TextField txtfZip;
	
	@FXML 
	private TextField txtfPhone;
	
	@FXML 
	private ComboBox cmbfAuthLevel;
	
	@FXML
	private Button btnSubmit;
	
	private ObservableList<String> authLevelStrings = AuthUtils.getAuthLevelStrings();
	
	private AdminInterface adminInterface;
	
	/**
	 * initialize the UI controls
	 * AuthorizationLevel combobox
	 */
	@FXML
	public void initialize() {
		cmbfAuthLevel.getItems().addAll(authLevelStrings);
		cmbfAuthLevel.setValue(authLevelStrings.get(1));
	}
	
	/**
	 * perform a validation on the form fields
	 * and add a new librarian into the database
	 */
	@FXML
	public void btnSubmit_click() {
		if(validate()) {
			Librarian librarian = new Librarian();
			librarian.setFirstName(txtfFirstName.getText());
			librarian.setLastName(txtfLastName.getText());
			librarian.setAddress(new Address(txtfStreet.getText(),txtfCity.getText(),
										txtfState.getText(),txtfZip.getText()));
			librarian.setPhoneNumber(txtfPhone.getText());
			
			AuthorizationLevel authLevel = AuthUtils.getAuthLevelFromString((String)cmbfAuthLevel.getValue());
			librarian.setRole(authLevel);
			
			adminInterface.addLibrarian(librarian);
		}
	}
	
	
	/**
	 * validate the fields before storing them into the database
	 * 
	 * @return true if all the inputs provided are validated, false otherwise
	 */
	private boolean validate() {
		if(ValidationUtils.isEmpty(txtfFirstName,txtfLastName,txtfStreet,txtfCity,txtfState,
				txtfZip,txtfPhone))
			return false;
		
		return true;
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
