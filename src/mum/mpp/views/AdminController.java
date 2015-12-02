package mum.mpp.views;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mum.mpp.ActionType;
import mum.mpp.tay.backendinterface.AdminIMP;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.utils.MapStringToView;

public class AdminController {
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
	}
	
	@SuppressWarnings("unchecked")
	private void initTreeMenu() {
		TreeItem<String> root = new TreeItem<>("Library Menu", rootIcon);
		root.setExpanded(true);
		
		TreeItem<String> ad = new TreeItem<>("Administrator");
		ad.setExpanded(true);
		TreeItem<String> lib = new TreeItem<>("Librarian");
		lib.setExpanded(true);
		TreeItem<String> mem = new TreeItem<>("Member");
		mem.setExpanded(true);
		
		root.getChildren().addAll(ad, lib, mem);
		
		TreeItem<String> adAdmin = new TreeItem<>("New Administrator");
		TreeItem<String> editAdmin = new TreeItem<>("Edit Administrator");
		ad.getChildren().addAll(adAdmin, editAdmin);
		
		TreeItem<String> adLib = new TreeItem<>("New Librarian");
		TreeItem<String> editLib = new TreeItem<>("Edit Librarian");
		TreeItem<String> delLib = new TreeItem<>("Delete Librarian");
		lib.getChildren().addAll(adLib, editLib, delLib);
		
		TreeItem<String> adMem = new TreeItem<>("New Member");
		TreeItem<String> editMem = new TreeItem<>("Edit Member");
		TreeItem<String> delMem = new TreeItem<>("Delete Mem");
		mem.getChildren().addAll(adMem, editMem, delMem);
		
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
		
		BorderPane root = (BorderPane)loader.load();
		contentArea.setCenter(root);
		
		delegateAdminInterface(loader, actionType);
	}
	
	private void delegateAdminInterface(FXMLLoader loader, ActionType actionType) {
		if(actionType == ActionType.ADD_ADMIN) {
			AddAdministratorController c = (AddAdministratorController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.EDIT_ADMIN) {
			EditAdministratorController c = (EditAdministratorController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.ADD_LIBRARIAN) {
			AddLibrarianController c = (AddLibrarianController)loader.getController();
			c.setAdminInterface(adminInterface);
		} else if(actionType == ActionType.EDIT_LIBRARIAN) {
			EditLibrarianController c = (EditLibrarianController)loader.getController();
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
