package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
	
	private ObservableList<String> authLevelStrings = FXCollections.observableArrayList("ADMIN", "LIBRARIAN", "BOTH");
	
	@FXML
	public void initialize() {
		cmbfAuthLevel.getItems().addAll(authLevelStrings);
		cmbfAuthLevel.setValue(authLevelStrings.get(1));
	}
	
	@FXML
	public void btnSubmit_click() {
		
	}
}
