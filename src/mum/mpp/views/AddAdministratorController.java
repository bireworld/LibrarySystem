package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Admin;

public class AddAdministratorController {
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
	
	private AdminInterface adminInterface;
	
	private ObservableList<String> authLevelStrings = FXCollections.observableArrayList("ADMIN", "LIBRARIAN", "BOTH");
	
	@FXML
	public void initialize() {
		cmbfAuthLevel.getItems().addAll(authLevelStrings);
		cmbfAuthLevel.setValue(authLevelStrings.get(0));
	}
	
	@FXML
	public void btnSubmit_click() {
		System.out.println("Add Administrator click");
		
		/** 
		 * validation still left to do
		 */
		
		Admin admin = new Admin();
		admin.setFirstName(txtfFirstName.getText());
		admin.setLastName(txtfLastName.getText());
		admin.setAddress(new Address(txtfStreet.getText(),txtfCity.getText(),
				txtfState.getText(), txtfZip.getText()));
		admin.setPhoneNumber(txtfPhone.getText());
		
		try {
			adminInterface.addAdmin(admin);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
