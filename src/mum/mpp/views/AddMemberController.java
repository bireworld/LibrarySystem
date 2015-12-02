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
import mum.mpp.tay.entity.Member;

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
			adminInterface.addMember(member);
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
