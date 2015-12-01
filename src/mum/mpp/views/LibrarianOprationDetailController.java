package mum.mpp.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;

public class LibrarianOprationDetailController {
	private ObservableList<Book> checkoutBooks = FXCollections.observableArrayList();
	private ObservableList<BookCopy> bookcopys = FXCollections.observableArrayList();
	private Book bookDetail;

	@FXML
	private TableView<Book> personTable;
	@FXML
	private TableColumn<Book, String> firstNameColumn;
	@FXML
	private TableColumn<Book, String> lastNameColumn;
	@FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    
	@FXML
	public void btnLogin_click() {
		System.out.println("btnLogin_click");
	}
	@FXML
	public void btnLogin_click1() {
		System.out.println("btnLogin_click1");

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
