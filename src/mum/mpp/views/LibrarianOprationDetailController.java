package mum.mpp.views;

import java.util.Date;
import java.util.List;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import mum.mpp.tay.backendinterface.LibrarianInterface;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.vo.BookVo;

public class LibrarianOprationDetailController {
	private LibrarianInterface user;

	public void setUser(LibrarianInterface user) {
		this.user = user;
	}
	private ObservableList<BookVo> checkoutBooks = FXCollections.observableArrayList();
	private ObservableList<BookCopy> bookcopys = FXCollections.observableArrayList();
	private BookVo bookDetail;

	@FXML
	private TableView<BookVo> checkoutBooksTable;
	@FXML
	private TableColumn<BookVo, String> firstColumn;
	@FXML
	private TableColumn<BookVo, Number> secondColumn;
	@FXML
	private TableColumn<BookVo, Date> thirdColumn;
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
		
		initEnv();
		
		//validate
		String memberId = memberIdField.getText();
		if(null == memberId || "".equals(memberId)){
			memberErrMsg.setText("empty imput");
			return;
		}else{
			try{
				Long id = Long.valueOf(memberId);
				
				//TODO remove mock
				Member libMember = user.getMemberById(id);
				System.out.println(libMember);
				if(null == libMember){
					memberErrMsg.setText("Can not find by that ID");
					return;
				}else{
					System.out.println("btnLogin_click2");
					checkoutBooks.add(new BookVo("1", "1", 1));
					checkoutBooks.add(new BookVo("2", "2", 2));
					
					List<CheckoutRecord> memberRecord = user.getMemberRecord(id);
					for(CheckoutRecord checkoutRecord: memberRecord){
						Book book = checkoutRecord.getBook().getBook();
						BookVo bookVo = new BookVo(book.getiSBNNumber(),
								book.getTitle(), book.getMaximumCheckoutDurationInDays(), checkoutRecord.getBook().getCopyNumber(),
								checkoutRecord.getCheckoutDate(), checkoutRecord.getDueDate(), checkoutRecord.getCheckinDate());
						checkoutBooks.add(bookVo);
					}
					System.out.println(memberRecord.size());
			        // Add observable list data to the table
					checkoutBooksTable.setItems(checkoutBooks);

			        firstColumn.setCellValueFactory(cellData -> cellData.getValue().getiSBNNumber());
			        secondColumn.setCellValueFactory(cellData -> cellData.getValue().getCopyNumber());
			        thirdColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDate());
				}
			}catch(Exception e){
				memberErrMsg.setText("Pls input a number!");
				return;
			}
			
		}
		
		
	}

	private void initEnv() {
		//clear err msg
		memberErrMsg.setText("");

		//clear Data
		checkoutBooks = FXCollections.observableArrayList();
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
