package mum.mpp.views;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.mpp.beans.EditLibrarianSearchBean;
import mum.mpp.beans.EditMemberSearchBean;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

public class HomePageController {
	@FXML
	private TableView tblvLibrarians;
	
	@FXML
	private TableView tblvMembers;
	
	@FXML
	private TableColumn tblcMemberId;
	
	@FXML
	private TableColumn tblcMemFirstName;
	
	@FXML
	private TableColumn tblcMemLastName;
	
	@FXML
	private TableColumn tblcMemPhone;
	
	@FXML
	private TableColumn tblcNumBooksTaken;
	
	@FXML
	private TableColumn tblcLibsStaffId;
	
	@FXML
	private TableColumn tblcLibsFirstName;
	
	@FXML
	private TableColumn tblcLibsLastName;
	
	@FXML
	private TableColumn tblcLibsPhone;
	
	private AdminInterface adminInterface;
	
	private ObservableList<EditMemberSearchBean> allMembersList;
	private ObservableList<EditLibrarianSearchBean> allStaffList;
	
	@FXML
	public void initialize() {
		System.out.println("HomeController initialize");
		
		initTableView();
		
		startDataLoadThread();
	}
	
	@SuppressWarnings("unchecked")
	private void initTableView() {
		tblcMemberId.setCellValueFactory(new PropertyValueFactory<EditMemberSearchBean, String>("memberId"));
		tblcMemFirstName.setCellValueFactory(new PropertyValueFactory<EditMemberSearchBean, String>("firstName"));
		tblcMemLastName.setCellValueFactory(new PropertyValueFactory<EditMemberSearchBean, String>("lastName"));
		tblcMemPhone.setCellValueFactory(new PropertyValueFactory<EditMemberSearchBean, String>("phone"));
		tblcNumBooksTaken.setCellValueFactory(new PropertyValueFactory<EditMemberSearchBean, String>("numBooks"));
		
		tblcLibsStaffId.setCellValueFactory(new PropertyValueFactory<EditLibrarianSearchBean, String>("staffId"));
		tblcLibsFirstName.setCellValueFactory(new PropertyValueFactory<EditLibrarianSearchBean, String>("firstName"));
		tblcLibsLastName.setCellValueFactory(new PropertyValueFactory<EditLibrarianSearchBean, String>("lastName"));
		tblcLibsPhone.setCellValueFactory(new PropertyValueFactory<EditLibrarianSearchBean, String>("phone"));
	}
	
	private void startDataLoadThread() {
		Timer timer=new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				new Thread(() -> {
					loadDataMember();
					loadDataLibrarian();
				}).start();
			}
		};
		timer.schedule(task, 2000);
	}
	
	private void loadDataMember() {
		allMembersList = FXCollections.observableArrayList();
		List<Member> memberList = searchDataMember();
		if(memberList!=null) {
			for (Member member : memberList) {
				allMembersList.add(new EditMemberSearchBean(member.getUniqueMemberNumber(), member.getFirstName(),
						member.getLastName(), member.getPhoneNumber(), member.getAddress().getStreet(),
						member.getAddress().getCity(), member.getAddress().getState(), member.getAddress().getZip(),
						member.getRecords()));
				System.out.println(new EditMemberSearchBean(member.getUniqueMemberNumber(), member.getFirstName(),
						member.getLastName(), member.getPhoneNumber(), member.getAddress().getStreet(),
						member.getAddress().getCity(), member.getAddress().getState(), member.getAddress().getZip(),
						member.getRecords()));
				
			}
			
			tblvMembers.setItems(allMembersList);
		}
	}
	
	private void loadDataLibrarian() {
		allStaffList = FXCollections.observableArrayList();
		List<Staff> staffList = searchDataLibrarian();
		if(staffList!=null) {
			for (Staff staff : staffList) {
				if(staff.getRole()==AuthorizationLevel.LIBRARIAN)
					allStaffList.add(new EditLibrarianSearchBean(staff.getUniqueStaffId(), staff.getFirstName(),
						staff.getLastName(), staff.getPhoneNumber(), staff.getAddress().getStreet(),
						staff.getAddress().getCity(), staff.getAddress().getState(), staff.getAddress().getZip(),
						staff.getRole()));
				
			}
			
			tblvLibrarians.setItems(allStaffList);
		}
	}
	
	
	private List<Member> searchDataMember() {
		List<Member> allMemberList=null;
		try {
			System.out.println(adminInterface);
			allMemberList = adminInterface.getAllMembers();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return allMemberList;
	}
	
	private List<Staff> searchDataLibrarian() {
		List<Staff> allMemberList=null;
		try {
			System.out.println(adminInterface);
			allMemberList = adminInterface.getAllStaff();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return allMemberList;
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
}
