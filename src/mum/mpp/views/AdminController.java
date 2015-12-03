package mum.mpp.views;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mum.mpp.ActionType;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.utils.MapStringToView;

public class AdminController {
	@FXML
	private Label lblDateAndTime;
	
	@FXML
	private ImageView imgvLogo;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblSubTitle;
	
	@FXML
	private TreeView treeMenu;
	
	@FXML
	private BorderPane contentArea;
	
	private Stage primaryStage;
	private Scene scene;
	
	private AdminInterface adminInterface;
	
	private final Node rootIcon = new ImageView(
	        new Image(getClass().getResourceAsStream("/mum/mpp/images/folder.png"))
	    );

	
	@FXML
	public void initialize() {
		System.out.println("AdminController");
		initTreeMenu();
		startTicking();
	}
	
	private void startTicking() {
		LocalDate ld=LocalDate.now();
		lblDateAndTime.setText(ld.getMonth().toString()+" "+ld.getDayOfMonth()+", "+ld.getYear());
	}
	
	@SuppressWarnings("unchecked")
	private void initTreeMenu() {
		TreeItem<String> root = new TreeItem<>("Library Menu", rootIcon);
		root.setExpanded(true);
		
		TreeItem<String> home = new TreeItem<>("Home");
		
		TreeItem<String> st = new TreeItem<>("Staff");
		st.setExpanded(true);
		TreeItem<String> mem = new TreeItem<>("Member");
		mem.setExpanded(true);
		TreeItem<String> book = new TreeItem<>("Book");
		book.setExpanded(true);
		
		root.getChildren().addAll(home, st, mem, book);
		
		//TreeItem<String> adAdmin = new TreeItem<>("New Administrator");
		//TreeItem<String> editAdmin = new TreeItem<>("Edit Administrator");
		//ad.getChildren().addAll(adAdmin, editAdmin);
		
		TreeItem<String> adSt = new TreeItem<>("New Staff");
		TreeItem<String> editSt = new TreeItem<>("Edit Staff");
		st.getChildren().addAll(adSt, editSt);
		
		TreeItem<String> adMem = new TreeItem<>("New Member");
		TreeItem<String> editMem = new TreeItem<>("Edit Member");
		mem.getChildren().addAll(adMem, editMem);
		
		TreeItem<String> adBook = new TreeItem<>("Add Book");
		TreeItem<String> adCopy = new TreeItem<>("Add Book Copy");
		book.getChildren().addAll(adBook, adCopy);
		
		treeMenu.setRoot(root);
		
		treeMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {				
				try {
					handleTreeMenuClick(newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void handleTreeMenuClick(TreeItem<String> selectedItem) throws Exception {
		System.out.println(selectedItem.getValue());
		
		String view = MapStringToView.mapView(selectedItem.getValue());
		ActionType actionType = MapStringToView.mapAction(selectedItem.getValue());
		if(view!=null) loadContentArea(view, actionType);

	}
	
	private void loadContentArea(String fxmlName, ActionType actionType) throws IOException {
		String filePath = "/mum/mpp/views/"+fxmlName;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
		
		if(actionType!=ActionType.HOME) {
			BorderPane root = (BorderPane)loader.load();
			contentArea.setCenter(root);
			
			contentArea.setTop(null);
		} else {
			FlowPane root = (FlowPane)loader.load();
			contentArea.setCenter(root);
			
			Label label = new Label(MapStringToView.mapTitle(actionType));
			label.setId("lblHomeTop");
			StackPane pane=new StackPane();
			pane.setAlignment(Pos.BOTTOM_LEFT);
			pane.getChildren().add(label);
			contentArea.setTop(pane);
		}
		
		delegateAdminInterface(loader, actionType);
	}
	
	private void delegateAdminInterface(FXMLLoader loader, ActionType actionType) {
		if(actionType == ActionType.HOME) {
			HomePageController c = (HomePageController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.ADD_STAFF) {
			AddAdministratorController c = (AddAdministratorController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.EDIT_STAFF) {
			EditAdministratorController c = (EditAdministratorController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.ADD_MEMBER) {
			AddMemberController c = (AddMemberController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.EDIT_MEMBER) {
			EditMemberController c = (EditMemberController)loader.getController();
			c.setAdminInterface(adminInterface);
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
