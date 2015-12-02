package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Librarian;
import mum.mpp.tay.entity.Member;
import mum.mpp.utils.DialogUtil;

public class AddMemberController {
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
	
	//@FXML 
	//private ComboBox cmbfAuthLevel;
	
	@FXML
	private Button btnSubmit;
	
	private AdminInterface adminInterface;
	
	//private ObservableList<String> authLevelStrings = FXCollections.observableArrayList("ADMIN", "LIBRARIAN", "BOTH");
	
	@FXML
	public void initialize() {
		//cmbfAuthLevel.getItems().addAll(authLevelStrings);
		//cmbfAuthLevel.setValue(authLevelStrings.get(1));
	}
	
	@FXML
	public void btnSubmit_click() {
		System.out.println("Add Member click");
		
		/** 
		 * validation still left to do
		 */
		
		Member member = new Member();
		member.setFirstName(txtfFirstName.getText());
		member.setLastName(txtfLastName.getText());
		member.setAddress(new Address(txtfStreet.getText(),txtfCity.getText(),
				txtfState.getText(), txtfZip.getText()));
		member.setPhoneNumber(txtfPhone.getText());
		
		try {
			Member l = adminInterface.addMember(member);
			System.out.println("ret value "+l);
			if(l != null) {
				DialogUtil.showDialog("Add Member", "Member ID : "+l.getUniqueMemberNumber(),
						""+l.getFirstName()+" "+l.getLastName()+" added into the Library System.", AlertType.INFORMATION);
				clearFields();
			} else {
				DialogUtil.showDialog("Error", "Error adding new member",
						"Please try adding new member again after a few minutes.", AlertType.ERROR);
			}
		} catch (ServiceException e) {
			DialogUtil.showDialog("Error", "Error adding new member : "+e.getMessage(),
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
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
