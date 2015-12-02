package mum.mpp.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import mum.mpp.SearchBy;
import mum.mpp.beans.EditAdminSearchBean;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.Librarian;
import mum.mpp.tay.entity.Staff;

public class EditAdministratorController {
	@FXML
	private TextField txtfSearchBox;
	
	@FXML
	private RadioButton rbtnByAdminId;
	
	@FXML
	private RadioButton rbtnByName;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private TableView tblvSearchResult;
	
	@FXML
	private TableColumn tcolAdminId;
	
	@FXML
	private TableColumn tcolFirstName;
	
	@FXML
	private TableColumn tcolLastName;
	
	@FXML
	private TableColumn tcolPhone;
	
	@FXML 
	private TableColumn tcolStreet;
	
	@FXML
	private TableColumn tcolCity;
	
	@FXML
	private TableColumn tcolState;
	
	@FXML
	private TableColumn tcolZip;
	
	@FXML
	private Button btnSaveEdit;
	
	private AdminInterface adminInterface;
	
	private final ToggleGroup toggleGroup = new ToggleGroup();
	
	private ObservableList<EditAdminSearchBean> searchResult;// = FXCollections.observableArrayList();
	private List<Librarian> searchResultList;
	private Set<Integer> editPosSet;
	
	@FXML
	public void initialize() {
		System.out.println("Edit library initialize");
		
		initRadioButtons();
		initTableView();
	}
	
	private void initRadioButtons() {
		rbtnByName.setToggleGroup(toggleGroup);
		rbtnByAdminId.setToggleGroup(toggleGroup);
		
		rbtnByName.setSelected(true);
	}
	
	@SuppressWarnings("unchecked")
	private void initTableView() {
		
		tcolAdminId.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("adminId"));
		tcolFirstName.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("firstName"));
		tcolLastName.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("lastName"));
		tcolPhone.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("phone"));
		tcolStreet.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("street"));
		tcolCity.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("city"));
		tcolState.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("state"));
		tcolZip.setCellValueFactory(new PropertyValueFactory<EditAdminSearchBean, String>("zip"));
		
		/*tblvSearchResult.getSelectionModel()
			.selectedIndexProperty().addListener((ob,oldVal,newVal)->{
			System.out.println(newVal);
		});*/
		tblvSearchResult.setEditable(true);
		
		tcolFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolFirstName.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setFirstName((String)e.getNewValue());
				
				editPosSet.add(e.getTablePosition().getRow());
				//System.out.println(e.getTablePosition().getRow());
			}
		});
		
		tcolLastName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolLastName.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setLastName((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
		
		tcolPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolPhone.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setPhone((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
		
		tcolStreet.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolStreet.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setPhone((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
		
		tcolCity.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolCity.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setPhone((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
		
		tcolState.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolState.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setPhone((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
		
		tcolZip.setCellFactory(TextFieldTableCell.forTableColumn());
		tcolZip.setOnEditCommit(new EventHandler<CellEditEvent>() {
			@Override
			public void handle(CellEditEvent e) {
				((EditAdminSearchBean)e.getTableView().getItems()
						.get(e.getTablePosition().getRow()))
						.setPhone((String)e.getNewValue());
				editPosSet.add(e.getTablePosition().getRow());
			}
		});
	}
	
	@FXML
	public void btnSearch_click() {
		System.out.println("EditLibrarian search click");
		
		SearchBy searchByConstraint;
		if(rbtnByName.isSelected()) searchByConstraint=SearchBy.ByName;
		else searchByConstraint=SearchBy.ById;
		
		searchResult = FXCollections.observableArrayList();
		searchResultList = searchData(txtfSearchBox.getText(), searchByConstraint);
		for(Staff staff : searchResultList) {
			searchResult.add(new EditAdminSearchBean(staff.getUniqueStaffId(), staff.getFirstName(),
					staff.getLastName(), staff.getPhoneNumber(), staff.getAddress().getStreet(),
					staff.getAddress().getCity(), staff.getAddress().getState(), 
					staff.getAddress().getZip()));
			//System.out.println(staff.toString());
		}
		
		tblvSearchResult.setItems(searchResult);
		
		editPosSet = new HashSet<>();
	}
	
	@FXML
	public void btnSaveEdit_click() {
		System.out.println("EditLibrarian save edit click");
		
		Iterator<Integer> iterator=editPosSet.iterator();
		int editCount=0;
		while(iterator.hasNext()) {
			Integer pos = iterator.next();
			iterator.remove();
			//Admin l = adminInterface.editAdmin((Admin)searchResultList.get(pos));
			//if(l!=null) 
				editCount++;
		}
		System.out.println(editCount+ " edits saved.");
	}
	
	
	private List<Librarian> searchData(String searchString, SearchBy searchBy) {
		List<Librarian> list = new ArrayList<>();
		
		Librarian s1=new Librarian();
		s1.setUniqueStaffId(1);
		s1.setFirstName("Bob");
		s1.setLastName("Marley");
		s1.setPhoneNumber("646654431");
		s1.setAddress(new Address("4th St.","Fairfield","Iowa","56524"));
		
		Librarian s2=new Librarian();
		s2.setUniqueStaffId(1);
		s2.setFirstName("Harly");
		s2.setLastName("David");
		s2.setPhoneNumber("646654431");
		s2.setAddress(new Address("4th St.","Fairfield","Iowa","56524"));
		
		list.add(s1);
		list.add(s2);
		
		return list;
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
