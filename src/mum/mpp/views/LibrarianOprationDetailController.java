package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import mum.mpp.tay.backendinterface.LibrarianInterface;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.vo.Book;

public class LibrarianOprationDetailController {
	private LibrarianInterface user;

	public void setUser(LibrarianInterface user) {
		this.user = user;
	}
	private ObservableList<Book> checkoutBooks = FXCollections.observableArrayList();
	private ObservableList<BookCopy> bookcopys = FXCollections.observableArrayList();
	private Book bookDetail;

	@FXML
	private TableView<Book> checkoutBooksTable;
	@FXML
	private TableColumn<Book, String> firstColumn;
	@FXML
	private TableColumn<Book, String> lastColumn;
	@FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField ISBNField;
    
    @FXML
    private Text memberErrMsg;
    @FXML
    private Text ISBNErrMsg;
    

	@FXML
	public void searchMemberInfo() {
		System.out.println("searchMemberInfo");
		
		//clear err msg
		memberErrMsg.setText("");

		//clear Data
		checkoutBooks = FXCollections.observableArrayList();
		//validate
		String memberId = memberIdField.getText();
		if(null == memberId || "".equals(memberId)){
			memberErrMsg.setText("empty imput");
			return;
		}else{
			try{
				Long id = Long.valueOf(memberId);
				
				//TODO remove mock
//				Member libMember = null;
				Member libMember = user.getMemberById(id);
				System.out.println(libMember);
				if(null == libMember){
					memberErrMsg.setText("Can not find by that ID");
					return;
				}else{
					System.out.println("btnLogin_click2");
					checkoutBooks.add(new Book("1", "1", 1));
					checkoutBooks.add(new Book("2", "2", 2));
					
			        // Add observable list data to the table
					checkoutBooksTable.setItems(checkoutBooks);
					
			        firstColumn.setCellValueFactory(cellData -> cellData.getValue().getiSBNNumber());
			        lastColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
				}
			}catch(Exception e){
				memberErrMsg.setText("Pls input a number!");
				return;
			}
			
		}
		
		
	}
	
	@FXML
	public void btnLogin_click() {
		System.out.println("btnLogin_click");
	}
	@FXML
	public void btnLogin_click2() {
		System.out.println("btnLogin_click2");

	}
	@FXML
	public void btnLogin_click3() {
		System.out.println("btnLogin_click3");

	}
}
