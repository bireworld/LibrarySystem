package mum.mpp.views;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.mpp.beans.EditAuthorSearchBean;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.Book;
import mum.mpp.utils.DialogUtil;

public class AddBookController {
	@FXML
	private TextField txtfTitle;
	
	@FXML
	private TextField txtfIsbn;
	
	@FXML
	private ComboBox cmbfMaxCheckoutDuration;
	
	@FXML
	private TextField txtfAuthFirstName;
	
	@FXML
	private TextField txtfAuthLastName;
	
	@FXML
	private TextField txtfAuthPhone;
	
	@FXML
	private TextField txtfAuthStreet;
	
	@FXML
	private TextField txtfAuthCity;
	
	@FXML
	private TextField txtfAuthState;
	
	@FXML
	private TextField txtfAuthZip;
	
	@FXML
	private TextArea txtaAuthCredentials;
	
	@FXML
	private TextArea txtaAuthShortBiography;
	
	@FXML
	private TableView tblvAuthorList;
	
	@FXML
	private TableColumn tblcFirstName;
	
	@FXML
	private TableColumn tblcLastName;
	
	@FXML
	private TableColumn tblcPhone;
	
	@FXML
	private Button btnAddAuthor;
	
	@FXML
	private Button btnAddBook;
	
	private AdminInterface adminInterface;
	
	private ObservableList<Integer> maxDurationList;
	
	private ObservableList<EditAuthorSearchBean> editAuthorList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		initComboMaxDuration();
		
		initTableView();
	}
	
	@SuppressWarnings("unchecked")
	private void initComboMaxDuration() {
		maxDurationList = FXCollections.observableArrayList();
		for(int i=1; i < 50; i++)
			maxDurationList.add(i);
		
		cmbfMaxCheckoutDuration.setItems(maxDurationList);
		cmbfMaxCheckoutDuration.setValue(7);
	}
	
	@FXML
	public void btnAddAuthor_click() {
		/**
		 * validation still left
		 */
		
		EditAuthorSearchBean author = new EditAuthorSearchBean(-1, txtfAuthFirstName.getText(), txtfAuthLastName.getText(),
				txtfAuthPhone.getText(), txtfAuthStreet.getText(), txtfAuthCity.getText(), txtfAuthState.getText(),
				txtfAuthZip.getText(), txtaAuthCredentials.getText(), txtaAuthShortBiography.getText());
		
		editAuthorList.add(author);
		
		tblvAuthorList.setItems(editAuthorList);
		
		clearFieldsAddAuthor();
	}
	
	@SuppressWarnings("unchecked")
	private void initTableView() {
		tblcFirstName.setCellValueFactory(new PropertyValueFactory<EditAuthorSearchBean, String>("firstName"));
		tblcLastName.setCellValueFactory(new PropertyValueFactory<EditAuthorSearchBean, String>("lastName"));
		tblcPhone.setCellValueFactory(new PropertyValueFactory<EditAuthorSearchBean, String>("phone"));
	}
	
	@FXML
	public void btnAddBook_click() {
		List<Author> authorList=new ArrayList<>();
		for(EditAuthorSearchBean editAuthorBean : editAuthorList) {
			Author author=new Author();
			author.setFirstName(editAuthorBean.getFirstName());
			author.setLastName(editAuthorBean.getLastName());
			author.setPhoneNumber(editAuthorBean.getPhone());
			author.setAddress(new Address(editAuthorBean.getStreet(), editAuthorBean.getCity(),
					editAuthorBean.getState(), editAuthorBean.getZip()));
			author.setCredentials(editAuthorBean.getCredentials());
			author.setShortBiography(editAuthorBean.getShortBio());
			
			authorList.add(author);
		}
		
		Book book=new Book(txtfIsbn.getText(), txtfTitle.getText(), authorList, 
				((Integer)cmbfMaxCheckoutDuration.getValue()).intValue(), null);
		
		try {
			Book newBook = adminInterface.addNewBook(book);
			if(newBook!=null) {
				String msg = newBook.getTitle() + ", ISBN: "+newBook.getiSBNNumber()+"\nAuthor: "+newBook.getAuthors().toString();
				DialogUtil.showDialog("Success", "The following new book has been added:", msg, AlertType.ERROR);
				clearFieldsAddBook();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			DialogUtil.showDialog("Error!", "Error Adding New Book!", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void clearFieldsAddBook() {
		txtfTitle.setText("");
		txtfIsbn.setText("");
		initComboMaxDuration();
		editAuthorList=FXCollections.observableArrayList();
		tblvAuthorList.setItems(editAuthorList);
	}
	
	private void clearFieldsAddAuthor() {
		txtfAuthFirstName.setText("");
		txtfAuthLastName.setText("");
		txtfAuthPhone.setText("");
		txtfAuthStreet.setText("");
		txtfAuthCity.setText("");
		txtfAuthState.setText("");
		txtfAuthZip.setText("");
		txtaAuthCredentials.setText("");
		txtaAuthShortBiography.setText("");
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
