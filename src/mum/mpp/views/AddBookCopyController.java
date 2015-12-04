package mum.mpp.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import mum.mpp.beans.EditBookSearchBean;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.utils.DialogUtil;

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
	private TableView<EditBookSearchBean> tblvBookSearch;
	
	@FXML
	private TableColumn<EditBookSearchBean, String> tblcISBN;
	
	@FXML
	private TableColumn<EditBookSearchBean, String> tblcBookTitle;
	
	@FXML
	private TableColumn<EditBookSearchBean, String> tblcNumBookCopy;
	
	@FXML
	private TableColumn<EditBookSearchBean, String> tblcMaxCheckoutDuration;
	
	@FXML
	private VBox vboxRightPane;
	
	@FXML
	private Label lblrTitle;
	
	@FXML
	private Label lblrISBN;
	
	@FXML
	private Label lblrNumCopies;
	
	@FXML
	private Label lblrAuthors;	
	
	@FXML
	private ComboBox cmbfNumCopiesToAdd;
	
	@FXML
	private Button btnAddBookCopies;
	
	private ToggleGroup searchToggleGroup;

	private AdminInterface adminInterface;
	
	private ObservableList<EditBookSearchBean> editBookSearchList;
	
	private ObservableList<Integer> numCopiesList;
	
	private Set<Integer> editPosSet;
	
	private int currentRowPos;
	
	@FXML
	private Label lblr1;
	
	@FXML
	public void initialize() {
		initRadioButtons();
		initTableView();
		
		hideRightPanel();
	}
	
	private void hideRightPanel() {
		lblr1.setVisible(false);
		cmbfNumCopiesToAdd.setVisible(false);
		btnAddBookCopies.setVisible(false);
		
		vboxRightPane.setVisible(false);
	}
	
	private void showRightPanel() {
		lblr1.setVisible(true);
		cmbfNumCopiesToAdd.setVisible(true);
		btnAddBookCopies.setVisible(true);
		
		vboxRightPane.setVisible(true);
	}
	
	private void initRadioButtons() {
		searchToggleGroup = new ToggleGroup();
		rbtnByTitle.setToggleGroup(searchToggleGroup);
		rbtnByISBN.setToggleGroup(searchToggleGroup);
		
		rbtnByTitle.setSelected(true);
	}
	
	private void initTableView() {
		tblcISBN.setCellValueFactory(new PropertyValueFactory<EditBookSearchBean, String>("isbn"));
		tblcBookTitle.setCellValueFactory(new PropertyValueFactory<EditBookSearchBean, String>("title"));
		tblcNumBookCopy.setCellValueFactory(new PropertyValueFactory<EditBookSearchBean, String>("numCopies"));
		tblcMaxCheckoutDuration.setCellValueFactory(new PropertyValueFactory<EditBookSearchBean, String>("maxCheckoutDuration"));
		
		tblvBookSearch.getSelectionModel().selectedIndexProperty()
		 .addListener((ob,oldVal,newVal)->{
			if(newVal.intValue()>=0) {
				AddBookCopyController.this.currentRowPos = newVal.intValue();
				AddBookCopyController.this.handleItemSelection(newVal.intValue());
			}
			
		});
	}
	
	private void handleItemSelection(int pos) {
		numCopiesList=FXCollections.observableArrayList();
		for(int i=1;i<50;i++)
			numCopiesList.add(new Integer(i));
		
		cmbfNumCopiesToAdd.setItems(numCopiesList);
		cmbfNumCopiesToAdd.setValue(1);
		
		showRightPanel();
		
		EditBookSearchBean book = editBookSearchList.get(pos);
		
		lblrTitle.setText(book.getTitle());
		lblrISBN.setText("ISBN : "+book.getIsbn());
		lblrNumCopies.setText("Number of copies : "+book.getNumCopies());
		
		List<Author> listAuthors=book.getListAuthors();
		StringBuilder sb=new StringBuilder("*****************\nAuthors :\n");
		for(Author author: listAuthors) {
			sb.append(author.getFirstName()+" "+author.getLastName()+"\n"
					+author.getAddress().getCity()+", "+author.getAddress().getState());
			sb.append("\n\n");
		}
		lblrAuthors.setText(sb.toString());
	}
	
	@FXML
	public void btnAddCopies_click() {
		EditBookSearchBean editBook = editBookSearchList.get(currentRowPos);
		Book book = editBook.getBook();
		BookCopy temp=null;
		int c=(Integer)cmbfNumCopiesToAdd.getValue();
		int countBooksAdded=0;
		for(int i=1;i<=c;i++) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(book);
			bookCopy.setCopyNumber(editBook.getNumCopies()+i);
			try {
				BookCopy cp = adminInterface.addBookCopy(bookCopy);
				temp=cp;
				countBooksAdded++;
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	
		List<Author> listAuthors=book.getAuthors();
		StringBuilder sb=new StringBuilder("*****************\nAuthors :\n");
		for(Author author: listAuthors) {
			sb.append(author.getFirstName()+" "+author.getLastName()+"\n"
					+author.getAddress().getCity()+", "+author.getAddress().getState());
			sb.append("\n\n");
		}
		
		String copyNumber = "";
		if(temp!=null) copyNumber=temp.getCopyNumber()-countBooksAdded+" - "+temp.getCopyNumber();
		String msg = book.getTitle() + ", ISBN: "+book.getiSBNNumber()
				+"\nAuthor: "+sb.toString()+"\nCopy Number : "+copyNumber;
		DialogUtil.showDialog("Success", "The following book copy has been added:", msg, AlertType.INFORMATION);
		clearFieldsAddBookCopy();
		
	}
	
	private void clearFieldsAddBookCopy() {
		txtfSearchBox.setText("");
		initRadioButtons();
		currentRowPos=0;
		editBookSearchList=FXCollections.observableArrayList();
		tblvBookSearch.setItems(editBookSearchList);
		
		lblrTitle.setText("");
		lblrISBN.setText("");
		lblrAuthors.setText("");
		lblrNumCopies.setText("");
		cmbfNumCopiesToAdd.setValue(1);
		hideRightPanel();
	}
	
	@FXML
	public void btnSearch_click() {
		loadTableView();
	}
	
	private void loadTableView() {
		List<Book> bookList;
		
		if(rbtnByTitle.isSelected()) bookList = loadSearchData(txtfSearchBox.getText(), 1);
		else bookList = loadSearchData(txtfSearchBox.getText(), 2);
		
		editBookSearchList = FXCollections.observableArrayList();
		
		for (Book book : bookList) {
			editBookSearchList.add(new EditBookSearchBean(book.getiSBNNumber(), book.getTitle(),
					book.getMaximumCheckoutDurationInDays(), book.getAuthors(), book.getCopies()));
		}

		tblvBookSearch.setItems(editBookSearchList);

		editPosSet = new HashSet<>();
	}
	
	private List<Book> loadSearchData(String searchString, int searchType) {
		List<Book> list = new ArrayList<>();

		if (searchType == 2) {
			try {
				Book l = adminInterface.getBookByISBN(searchString);
				if (l != null)
					list.add(l);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else if (searchType == 1) {
			try {
				List<Book> tempList = adminInterface.getBookByName(searchString);

				if(tempList!=null)
					tempList.forEach(l->list.add(l));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
