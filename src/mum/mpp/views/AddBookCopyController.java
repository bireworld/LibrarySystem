package mum.mpp.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import mum.mpp.tay.backendinterface.AdminInterface;

public class AddBookCopyController {
	@FXML
	private TextField txtfSearchBox;
	
	@FXML
	private RadioButton rbtnByTitle;
	
	@FXML
	private RadioButton rbtnByISBN;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private TableView tblvBookSearch;
	
	@FXML
	private TableColumn tblcISBN;
	
	@FXML
	private TableColumn tblcBookTitle;
	
	@FXML
	private TableColumn tblcNumBookCopy;
	
	@FXML
	private TableColumn tblcMaxCheckoutDuration;
	
	
	private ToggleGroup searchToggleGroup;

	private AdminInterface adminInterface;
	
	@FXML
	public void initialize() {
		//initRadioButtons();
	}
	
	

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
