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
import mum.mpp.beans.EditMemberSearchBean;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;

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
	
	private AdminInterface adminInterface;
	
	private ObservableList<EditMemberSearchBean> allMembersList;
	
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
	}
	
	private void startDataLoadThread() {
		Timer timer=new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				new Thread(() -> {
					loadData();
				}).start();
			}
		};
		timer.schedule(task, 2000);
	}
	
	private void loadData() {
		allMembersList = FXCollections.observableArrayList();
		List<Member> memberList = searchData();
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
	
	
	private List<Member> searchData() {
		List<Member> allMemberList=null;
		try {
			System.out.println(adminInterface);
			allMemberList = adminInterface.getAllMembers();
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
