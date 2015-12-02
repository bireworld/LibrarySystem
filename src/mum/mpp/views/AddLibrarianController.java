package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Staff;
import mum.mpp.utils.AuthUtils;
import mum.mpp.utils.DialogUtil;

public class AddLibrarianController {
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
	
	private ObservableList<String> authLevelStrings = FXCollections.observableArrayList("ADMIN", "LIBRARIAN", "FULLACCESS");
	
	@FXML
	public void initialize() {
		cmbfAuthLevel.getItems().addAll(authLevelStrings);
		cmbfAuthLevel.setValue(authLevelStrings.get(1));
	}
	
	@FXML
	public void btnSubmit_click() {
		System.out.println("add library click");
		
		/** 
		 * validation still left to do
		 */
		
		Staff librarian = new Staff();
		librarian.setFirstName(txtfFirstName.getText());
		librarian.setLastName(txtfLastName.getText());
		librarian.setAddress(new Address(txtfStreet.getText(),txtfCity.getText(),
				txtfState.getText(), txtfZip.getText()));
		librarian.setPhoneNumber(txtfPhone.getText());
		AuthorizationLevel authLevel = AuthUtils.getAuthLevelFromString((String)cmbfAuthLevel.getValue());
		librarian.setRole(authLevel);
		
		try {
			Staff l = adminInterface.addStaff(librarian);
			System.out.println("ret value "+l);
			if(l != null) {
				DialogUtil.showDialog("Add Librarian", "Librarian ID : "+l.getUniqueStaffId(),
						""+l.getFirstName()+" "+l.getLastName()+" added into the Library System.", AlertType.INFORMATION);
				clearFields();
			} else {
				DialogUtil.showDialog("Error", "Error adding new librarian",
						"Please try adding new librarian again after a few minutes.", AlertType.ERROR);
			}
		} catch (ServiceException e) {
			DialogUtil.showDialog("Error", "Error adding new librarian : "+e.getMessage(),
					e.getStackTrace().toString(), AlertType.ERROR);
		}
	}
	
	private void clearFields() {
		txtfFirstName.setText("");
		txtfLastName.setText("");
		txtfStreet.setText("");
		txtfCity.setText("");
		txtfState.setText("");
		txtfZip.setText("");
		txtfPhone.setText("");
		
		cmbfAuthLevel.setValue(authLevelStrings.get(1));
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
