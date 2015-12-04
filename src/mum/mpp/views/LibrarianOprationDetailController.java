package mum.mpp.views;

import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontBuilder;
import javafx.scene.text.Text;
import mum.mpp.tay.backendinterface.LibrarianInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.Author;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.vo.CheckoutRecordVO;
import mum.mpp.tay.vo.SearchedBookVO;
import mum.mpp.utils.DialogUtil;

public class LibrarianOprationDetailController {
	private LibrarianInterface user;

	public void setUser(LibrarianInterface user) {
		this.user = user;
	}

	private ObservableList<CheckoutRecordVO> checkoutBooks = FXCollections.observableArrayList();
	private ObservableList<SearchedBookVO> searchedBookVOs = FXCollections.observableArrayList();
	private ObservableList<BookCopy> bookcopys = FXCollections.observableArrayList();
	private CheckoutRecordVO bookDetail;

	@FXML
	private TableView<CheckoutRecordVO> checkoutBooksTable;
	@FXML
	private TableColumn<CheckoutRecordVO, String> firstColumn;
	@FXML
	private TableColumn<CheckoutRecordVO, Number> secondColumn;
	@FXML
	private TableColumn<CheckoutRecordVO, Date> thirdColumn;
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
	// book.getiSBNNumber(),
	// book.getTitle(), book.getMaximumCheckoutDurationInDays(),
	// checkoutRecord.getBook().getCopyNumber(),
	// checkoutRecord.getCheckoutDate(), checkoutRecord.getDueDate(),
	// checkoutRecord.getCheckinDate());
	@FXML
	private TextField memberIdField;
	@FXML
	private TextField iSBNField;
	@FXML
	private TextField searchBookNameField;
	// @FXML
	// private Button searchBookNameButton;

	@FXML
	private Text memberErrMsg;
	@FXML
	private Text ISBNErrMsg;
	@FXML
	private Button checkoutBtn;
	@FXML
	private Button checkinBtn;

	private boolean memberIdValid = false;
	private boolean isbnNoValid = false;
	private int selectedIndex = -1;
	private CheckoutRecordVO selectedRow = null;

	public void searchMemberInfo() {
		System.out.println("searchMemberInfo");
		initChkoutRecordEnv();

		// validate
		String memberId = memberIdField.getText();
		if (null == memberId || "".equals(memberId)) {
			memberErrMsg.setText("Empty imput");
			memberErrMsg.setFill(Color.RED);
			return;
		} else {
			try {

				Long id = Long.valueOf(memberId);

				// TODO remove mock
				Member libMember = user.getMemberById(id);

				if (null == libMember) {
					memberErrMsg.setText("Can not find by that ID");
					memberErrMsg.setFill(Color.RED);
					return;
				} else {
					// System.out.println("btnLogin_click2");
					// checkoutBooks.add(new BookVo("1", "1", 1));
					// checkoutBooks.add(new BookVo("2", "2", 2));
					memberErrMsg.setText("Valid");
					memberErrMsg.setFill(Color.GREEN);
					memberIdValid = true;
					if (isbnNoValid)
						checkoutBtn.setDisable(false);

					List<CheckoutRecord> memberRecord = user.getMemberRecord(id);
					for (CheckoutRecord checkoutRecord : memberRecord) {
						Book book = checkoutRecord.getBook().getBook();
						CheckoutRecordVO bookVo = new CheckoutRecordVO(checkoutRecord.getId(), book.getiSBNNumber(),
								book.getTitle(), book.getMaximumCheckoutDurationInDays(),
								checkoutRecord.getBook().getCopyNumber(), checkoutRecord.getCheckoutDate(),
								checkoutRecord.getDueDate(), checkoutRecord.getCheckinDate());

						checkoutBooks.add(bookVo);

					}

					// Add observable list data to the table
					checkoutBooksTable.setItems(checkoutBooks);

					firstColumn.setCellValueFactory(cellData -> cellData.getValue().getiSBNNumber());
					secondColumn.setCellValueFactory(cellData -> cellData.getValue().getCopyNumber());
					thirdColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDate());
				}
			} catch (ServiceException e) {
				memberErrMsg.setText(e.getMessage());
				memberErrMsg.setFill(Color.RED);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				memberErrMsg.setText("Pls input a number!");
				memberErrMsg.setFill(Color.RED);
				return;
			}

		}

	}

	private void initChkoutRecordEnv() {
		// clear err msg
		memberErrMsg.setText("");
		memberErrMsg.setFill(Color.BLACK);
		// clear Data
		checkoutBooks.clear();
		// reset state
		memberIdValid = false;
	}

	private void bindISBNFieldEvent(Boolean newValue) {
		System.out.println("bindISBNFieldEvent" + newValue);
		if (!newValue) {
			initISBNEnv();

			// validate
			String iSBNNo = iSBNField.getText();
			if (null == iSBNNo || "".equals(iSBNNo)) {
				ISBNErrMsg.setText("Empty imput");
				ISBNErrMsg.setFill(Color.RED);
				return;
			} else {
				try {
					boolean bookAvailable = user.isBookAvailable(iSBNNo);
					if (bookAvailable) {
						isbnNoValid = true;
						if (memberIdValid)
							checkoutBtn.setDisable(false);
						ISBNErrMsg.setText("Available");
						ISBNErrMsg.setFill(Color.GREEN);
					} else {
						ISBNErrMsg.setText("Not available");
						ISBNErrMsg.setFill(Color.YELLOW);
					}
					return;
				} catch (ServiceException e) {
					ISBNErrMsg.setText(e.getMessage());
					ISBNErrMsg.setFill(Color.RED);
					return;
				}
			}
		}

		// return null;
	}

	private void initISBNEnv() {
		// clear err msg
		ISBNErrMsg.setText("");
		ISBNErrMsg.setFill(Color.BLACK);
		// //clear Data
		// checkoutBooks.clear();
		// reset state
		isbnNoValid = false;

	}

	private void showChkOutRecordsDetails(CheckoutRecordVO book) {
		int newIndex = checkoutBooksTable.getSelectionModel().getSelectedIndex();
		if (newIndex != -1)
			selectedIndex = newIndex;
		if (null != checkoutBooksTable.getSelectionModel().getSelectedItem())
			selectedRow = checkoutBooksTable.getSelectionModel().getSelectedItem();
		if (book != null) {
			// Fill the labels with info from the book object.

			isbnNumberLabel.setText(book.getiSBNNumber().get());
			titleLabel.setText(book.getTitle().get());
			keepDaysLabel.setText(String.valueOf(book.getMaximumCheckoutDurationInDays().get()));
			copyNumberLabel.setText(String.valueOf(book.getCopyNumber().get()));
			checkoutDate.setText(book.getCheckoutDate().get().toString());
			dueDate.setText(book.getDueDate().get().toString());
			Object object = book.getCheckinDate().get();
			if (null == object) {
				checkinBtn.setVisible(true);
				checkinDate.setText("");
			} else {
				checkinDate.setText(object.toString());
			}

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
		// Listen for selection changes and show the person details when
		// changed.
		checkoutBooksTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showChkOutRecordsDetails(newValue));

		// onblur for member id
		memberIdField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue)
				// {
				// System.out.println("Textfield on focus");
				// }
				// else
				{
					searchMemberInfo();
				}
			}
		});

		iSBNField.focusedProperty().addListener((observable, oldValue, newValue) -> bindISBNFieldEvent(newValue));

		// checkoutBtn.disableProperty().addListener(arg0);
		checkoutBtn.setDisable(true);

		makeItdynamic();

	}

	@FXML
	public void btnCheckout_click() {
		if (!searchBookNameField.getText().isEmpty()) {
			DialogUtil.showDialog("Enter Something", "", "Please enter whole are part of book name", AlertType.WARNING);
			return;
		}
		String inputName = searchBookNameField.getText();

		try {
			user.getBookByName(inputName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void btnCheckin_click() {
		System.out.println("btnCheckin_click");
		if (null == checkinDate.getText() || "".equalsIgnoreCase(checkinDate.getText())) {
			try {
				// checkoutBooksTable.getSelectionModel().getSelectedItem()
				user.checkIn(selectedRow.getId());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		searchMemberInfo();
		// bindISBNFieldEvent(false);

		checkoutBooksTable.getSelectionModel().select(selectedIndex);
	}

	Node componentsPane;
	boolean isCheckInShow = true;

	private void makeItdynamic() {

		componentsPane = mainSplitPane.getItems().get(2);

		checkoutHeaderAnchorPane.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				workSpaceLable.setText("Checkout Records");
				searchedBookAnchor.setVisible(false);

				if (!isCheckInShow) {
					((AnchorPane) componentsPane).setMinHeight(421);
					mainSplitPane.getItems().add(2, componentsPane);

					isCheckInShow = true;
				}

			}
		});

		searchBookHeaderAnchorPane.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				workSpaceLable.setText("Searched Books");
				searchedBookAnchor.setVisible(true);
				if (isCheckInShow) {
					mainSplitPane.getItems().remove(componentsPane);
					isCheckInShow = false;
				}
			}
		});

		searchBookNameField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Search");
				searchedBookVOs.clear();
				List<Book> books;
				try {
					books = user.getBookByName(newValue);
					System.out.println("Search size:" + books.size());
					for (Book b : books) {
						SimpleStringProperty authorsName = new SimpleStringProperty();
						String names = "";
						for (Author a : b.getAuthors()) {
							names += a.getFirstName() + " " + a.getLastName() + " ,";
						}
						authorsName.set(names);
						SearchedBookVO bookVO = new SearchedBookVO(new SimpleStringProperty(b.getiSBNNumber()),
								new SimpleStringProperty(b.getTitle()), authorsName,
								new SimpleIntegerProperty(b.getMaximumCheckoutDurationInDays()), null);
						searchedBookVOs.add(bookVO);
					}
					System.out.println("Search sizex:" + searchedBookVOs.size());
					searchedBookTable.setItems(searchedBookVOs);

					sBiSBNNumber.setCellValueFactory(cellData -> cellData.getValue().getiSBNNumber());
					sBauthors.setCellValueFactory(cellData -> cellData.getValue().getAuthors());
					sBtitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
					sBmaximumCheckoutDurationInDays
							.setCellValueFactory(cellData -> cellData.getValue().getMaximumCheckoutDurationInDays());

				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	@FXML
	private SplitPane mainSplitPane;

	@FXML
	private AnchorPane searchBookHeaderAnchorPane;
	@FXML
	private AnchorPane checkoutHeaderAnchorPane;

	@FXML
	private Label workSpaceLable;

	@FXML
	private AnchorPane checkOutAnchor;
	@FXML
	private AnchorPane searchedBookAnchor;

	@FXML
	private TableView<SearchedBookVO> searchedBookTable;

	@FXML
	private TableColumn<SearchedBookVO, String> sBiSBNNumber;
	@FXML
	private TableColumn<SearchedBookVO, String> sBtitle;
	@FXML
	private TableColumn<SearchedBookVO, String> sBauthors;
	@FXML
	private TableColumn<SearchedBookVO, Number> sBmaximumCheckoutDurationInDays;

}
