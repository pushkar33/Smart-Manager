package status;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import googler.traineeBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class feeStatusViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private RadioButton radBal;

    @FXML
    private RadioButton radFullyPaid;

    @FXML
    private ComboBox<String> comboTech;

    @FXML
    private TableView<traineeBean> tblView;

    @FXML
    void doShowStatus(ActionEvent event) {
    	
    	ObservableList<traineeBean> objary=FXCollections.observableArrayList();
    	
    	if(radFullyPaid.isSelected()==true)
    	{
    		
    		objary=getFullyPaid();
    		tblView.getItems().clear();
    		tblView.setItems(objary);
    	}
    		
    	
    	else
    		if(radBal.isSelected()==true)
    	{
    		
    		objary=getBal();
    		tblView.getItems().clear();
    		tblView.setItems(objary);
    	}
    	else
    		getMsg("Please Make a Choice");
    	
    	
    	
    	
    }
    
    @FXML
    void doChangeBal(ActionEvent event) {
    	
    	radFullyPaid.setSelected(false);

    }

    @FXML
    void doChangeFull(ActionEvent event) {
    	
    	radBal.setSelected(false);

    }

    
    void doAddCols() // adding columns to tableView 
    {
    	TableColumn<traineeBean, String> tidCol=new TableColumn<traineeBean, String>("Trainee Id");
    	tidCol.setCellValueFactory(new PropertyValueFactory<>("tid"));//same as bean property
    	tidCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> nameCol=new TableColumn<traineeBean, String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	nameCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> mobileCol=new TableColumn<traineeBean, String>("Mobile");
    	mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobileCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> collegeCol=new TableColumn<traineeBean, String>("College");
    	collegeCol.setCellValueFactory(new PropertyValueFactory<>("college"));//same as bean property
    	collegeCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> semCol=new TableColumn<traineeBean, String>("Sem");
    	semCol.setCellValueFactory(new PropertyValueFactory<>("sem"));//same as bean property
    	semCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> branchCol=new TableColumn<traineeBean, String>("Branch");
    	branchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));//same as bean property
    	branchCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> techCol=new TableColumn<traineeBean, String>("Technology");
    	techCol.setCellValueFactory(new PropertyValueFactory<>("technology"));//same as bean property
    	techCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> batchdateCol=new TableColumn<traineeBean, String>("Batch Date");
    	batchdateCol.setCellValueFactory(new PropertyValueFactory<>("batchdate"));//same as bean property
    	batchdateCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> batchtimeCol=new TableColumn<traineeBean, String>("Batch Time");
    	batchtimeCol.setCellValueFactory(new PropertyValueFactory<>("batchtime"));//same as bean property
    	batchtimeCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, Integer> totalfeeCol=new TableColumn<traineeBean, Integer>("Total Fee");
    	totalfeeCol.setCellValueFactory(new PropertyValueFactory<>("totalfee"));//same as bean property
    	totalfeeCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, Integer> advfeeCol=new TableColumn<traineeBean, Integer>("Advance Fee");
    	advfeeCol.setCellValueFactory(new PropertyValueFactory<>("advancefee"));//same as bean property
    	advfeeCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, Integer> balanceCol=new TableColumn<traineeBean, Integer>("Balance");
    	balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));//same as bean property
    	balanceCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> picCol=new TableColumn<traineeBean, String>("PicPath");
    	picCol.setCellValueFactory(new PropertyValueFactory<>("picpath"));//same as bean property
    	picCol.setMinWidth(100);
    	
    	TableColumn<traineeBean, String> dofadmnCol=new TableColumn<traineeBean, String>("Admn Date");
    	dofadmnCol.setCellValueFactory(new PropertyValueFactory<>("dofadmn"));//same as bean property
    	dofadmnCol.setMinWidth(100);
    	
    	
    	tblView.getColumns().addAll(tidCol,nameCol,mobileCol,collegeCol,semCol,branchCol,techCol,batchdateCol,batchtimeCol,totalfeeCol,advfeeCol,balanceCol,picCol,dofadmnCol);
    }
    
    
    ObservableList<traineeBean> getFullyPaid() 
    {
      ObservableList<traineeBean> ary=FXCollections.observableArrayList();
      
      try {
    	  
		pst=con.prepareStatement("select * from trainees where balance=0 and technology=?");
		pst.setString(1, comboTech.getEditor().getText());
		ResultSet tableInMem=pst.executeQuery();
		
		while(tableInMem.next())
		{
			String tid=tableInMem.getString("tid");
			String name=tableInMem.getString("name");
			String mobile=tableInMem.getString("mobile");
			String college=tableInMem.getString("college");
			String sem=tableInMem.getString("sem");
			String branch=tableInMem.getString("branch");
			String technology=tableInMem.getString("technology");
			String batchdate=tableInMem.getString("batchdate");
			String batchtime=tableInMem.getString("batchtime");
			int totalfee=tableInMem.getInt("totalfee");
			int advancefee=tableInMem.getInt("advancefee");
			int balance=tableInMem.getInt("balance");
			String picpath=tableInMem.getString("picpath");
			String dofadmn=tableInMem.getDate("dofadmn").toString();
			
			traineeBean obj=new traineeBean(tid, name, mobile, college, sem, branch, technology, batchdate, batchtime, totalfee, advancefee, balance, picpath, dofadmn);
			ary.add(obj);
		}
	}
      catch (SQLException e) {
		
		e.printStackTrace();
	}
      return ary;
    }
    
    ObservableList<traineeBean> getBal()
    {
     
    	ObservableList<traineeBean> ary=FXCollections.observableArrayList();
     
    	try {
    		
			pst=con.prepareStatement("select * from trainees where technology=? and not balance=0");
			pst.setString(1, comboTech.getEditor().getText());
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String tid=tableInMem.getString("tid");
				String name=tableInMem.getString("name");
				String mobile=tableInMem.getString("mobile");
				String college=tableInMem.getString("college");
				String sem=tableInMem.getString("sem");
				String branch=tableInMem.getString("branch");
				String technology=tableInMem.getString("technology");
				String batchdate=tableInMem.getString("batchdate");
				String batchtime=tableInMem.getString("batchtime");
				int totalfee=tableInMem.getInt("totalfee");
				int advancefee=tableInMem.getInt("advancefee");
				int balance=tableInMem.getInt("balance");
				String picpath=tableInMem.getString("picpath");
				String dofadmn=tableInMem.getDate("dofadmn").toString();
				
				traineeBean obj=new traineeBean(tid, name, mobile, college, sem, branch, technology, batchdate, batchtime, totalfee, advancefee, balance, picpath, dofadmn);
				ary.add(obj);
			}
			
		}
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	return ary;
    }
    
    void getMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Important Message");
    	alert.setContentText(msg);
    	alert.show();
    }

    
    PreparedStatement pst;
    void dofetchTech()
    {
    	try {
    		
			pst=con.prepareStatement("select distinct batch from batches");
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String trBatch=tableInMem.getString("batch");
				comboTech.getItems().add(trBatch);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    Connection con;
    @FXML
    void initialize() {

    	con=ConnectToMysql.doConnect();
    	dofetchTech();
    	doAddCols();

    }

}
