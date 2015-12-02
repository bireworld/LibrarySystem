package mum.mpp.views;

import java.util.Date;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label isbnNumberLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label keepDaysLabel;
    @FXML
    private Label copyNumberLabel;
    @FXML
    private Label checkoutDate;
    @FXML
    private Label dueDate;
    @FXML
    private Label checkinDate;
//    book.getiSBNNumber(),
//	book.getTitle(), book.getMaximumCheckoutDurationInDays(), checkoutRecord.getBook().getCopyNumber(),
//	checkoutRecord.getCheckoutDate(), checkoutRecord.getDueDate(), checkoutRecord.getCheckinDate());
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
		
		initChkoutRecordEnv();
		
		//validate
		String memberId = memberIdField.getText();
		if(null == memberId || "".equals(memberId)){
			memberErrMsg.setText("empty imput");
			return;
		}else{
			try{
				System.out.println("btnLogin_click2 "+memberId);
				Long id = Long.valueOf(memberId);
				
				//TODO remove mock
				Member libMember = user.getMemberById(id);
				
				if(null == libMember){
					memberErrMsg.setText("Can not find by that ID");
					return;
				}else{
//					System.out.println("btnLogin_click2");
//					checkoutBooks.add(new BookVo("1", "1", 1));
//					checkoutBooks.add(new BookVo("2", "2", 2));
					
					List<CheckoutRecord> memberRecord = user.getMemberRecord(id);
					for(CheckoutRecord checkoutRecord: memberRecord){
						Book book = checkoutRecord.getBook().getBook();
						BookVo bookVo = new BookVo(book.getiSBNNumber(),
								book.getTitle(), book.getMaximumCheckoutDurationInDays(), checkoutRecord.getBook().getCopyNumber(),
								checkoutRecord.getCheckoutDate(), checkoutRecord.getDueDate(), checkoutRecord.getCheckinDate());
						System.out.println("btnLogin_click2 "+2);
						checkoutBooks.add(bookVo);
						System.out.println("btnLogin_click2 "+3);
					}

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

	private void initChkoutRecordEnv() {
		//clear err msg
		memberErrMsg.setText("");

		//clear Data
		checkoutBooks.clear();
	}
	
    private void showChkOutRecordsDetails(BookVo book) {
        if (book != null) {
            // Fill the labels with info from the book object.

        	isbnNumberLabel.setText(book.getiSBNNumber().get());
        	titleLabel.setText(book.getTitle().get());
        	keepDaysLabel.setText(String.valueOf(book.getMaximumCheckoutDurationInDays().get()));
        	copyNumberLabel.setText(String.valueOf(book.getCopyNumber().get()));
        	checkoutDate.setText(book.getCheckoutDate().get().toString());
        	dueDate.setText(book.getDueDate().get().toString());
        	checkinDate.setText(book.getCheckinDate().get() == null?"":book.getCheckinDate().get().toString());

        } else {
            // Person is null, remove all the text.
        	isbnNumberLabel.setText("");
        	titleLabel.setText("");
        	keepDaysLabel.setText("");
        	copyNumberLabel.setText("");
        	checkoutDate.setText("");
        	dueDate.setText("");
        	checkinDate.setText("");
        }
    }
	
    @FXML
    private void initialize() {
        // Listen for selection changes and show the person details when changed.
    	checkoutBooksTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChkOutRecordsDetails(newValue));
        memberIdField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
//                {
//                    System.out.println("Textfield on focus");
//                }
//                else
                {
                	searchMemberInfo();
                }
            }
        });
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
