package Admission;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import Batch.ConnectToMysql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewAddmissionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBranch;

    @FXML
    private ComboBox<String> comboCollege;

    @FXML
    private ComboBox<String> comboSem;

    @FXML
    private ComboBox<String> comboTech;


    @FXML
    private ImageView picTrainee;

    @FXML
    private TextField txtAdvFee;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtTotFee;

    @FXML
    void doClear(ActionEvent event) {

    	comboBranch.getEditor().setText("");
    	comboCollege.getEditor().setText("");
    	comboSem.getEditor().setText("");
    	comboTech.getEditor().setText("");
    	
    	txtDate.setText("");
    	txtTime.setText("");
    	txtTotFee.setText("");
    	txtMob.setText("");
    	txtName.setText("");
    	txtAdvFee.setText("");
    	
    }

    @FXML
    void doDelete(ActionEvent event) {
    	
    	try {
    		
			pst=con.prepareStatement("select tid,technology from trainees where name=? and mobile=?");
			pst.setString(1, txtName.getText());
			pst.setString(2, txtMob.getText());
			ResultSet tableInMem=pst.executeQuery();
			
			boolean jasoos=false;
			String TrId="";
			String Tech="";
			
			while(tableInMem.next()) {
				
				jasoos=true;
				TrId=tableInMem.getString("tid");// Fetching tid using mobile and name
				Tech=tableInMem.getString("technology");// Fetching technology using mobile and name
				
				
				
				
			}
				
			
			if(jasoos==false)
				getMsg("Please Enter Valid Name And Mobile Number");
			else
			{
				
				pst=con.prepareStatement("delete from trainees where tid=?");
				pst.setString(1, TrId);
				
			    int count=pst.executeUpdate();
			    
			    getMsg(count+" Record Deleted Successfully");
			    
			    updateBookedDecrement(Tech);
				
			}
			
			
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }
    
    String picpath="file:/D:/bce.jpg"; // using default picpath if user do not select a file
    
    public void start(Stage stage)
    {
    	
  	FileChooser filechooser=new FileChooser(); 
     	
     	File selectedFile=filechooser.showOpenDialog(stage);
     	
     	if (selectedFile != null) {
     	
     		// creating new image instance using selected file pathname
     		picpath=selectedFile.toURI().toString();
     		//System.out.println(picpath);
     		Image img=new Image(picpath);
     		
     		// Setting img to picview 
     		picTrainee.setImage(img);
     		
     	}
  }
    
    @FXML
    void doBrowse(ActionEvent event) {
    	
    	Stage stage=new Stage();
    	start(stage);

    }

    PreparedStatement pst;
    @FXML
    void doFetchTech(ActionEvent event) {
    	
    	try {
    		
			pst=con.prepareStatement("select * from batches where batch=?");
			pst.setString(1, comboTech.getEditor().getText());
			
			ResultSet tableInMem=pst.executeQuery();
			
			boolean jasoos=false;
			
			while(tableInMem.next()) {
				jasoos=true;
				Date dos=tableInMem.getDate("sdate");
				String time=tableInMem.getString("stime");
				int tFee=tableInMem.getInt("fee");
				
				txtDate.setText(dos.toString());
				txtTime.setText(time);
				txtTotFee.setText(String.valueOf(tFee));
				
				
			}
			
			if(jasoos==false)
				getMsg("Enter Valid Technology");
			
			
			
		}
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }
    
    

    @FXML
    void doSave(ActionEvent event) {
    	

    		
    		String tname=txtName.getText();
    		String nameSub=tname.substring(0, 3);
    		
    		String tmob=txtMob.getText();
    		String mobSub=tmob.substring(tmob.length()-2, tmob.length());
    		
    		Random random=new Random();
    		int rannum=random.nextInt(1000);
    		
    		StringBuilder traineeId=new StringBuilder();
    		
    		traineeId.append(nameSub);
    		traineeId.append(String.valueOf(rannum));
    		traineeId.append(mobSub);
    		
    		int trBalance=Integer.parseInt(txtTotFee.getText())-Integer.parseInt(txtAdvFee.getText());
    		
    		
    		
    		
			try {
				
				pst=con.prepareStatement("insert into trainees values(?,?,?,?,?,?,?,?,?,?,?,?,?,current_date())");
				pst.setString(1,traineeId.toString());
				pst.setString(2, tname);
				pst.setString(3, tmob);
				pst.setString(4,comboCollege.getEditor().getText());
				pst.setString(5, comboSem.getEditor().getText());
				pst.setString(6, comboBranch.getEditor().getText());
				pst.setString(7, comboTech.getEditor().getText());
				pst.setString(8, txtDate.getText());
				pst.setString(9, txtTime.getText());
				pst.setInt(10, Integer.parseInt(txtTotFee.getText()));
				pst.setInt(11,Integer.parseInt(txtAdvFee.getText()));
				pst.setInt(12, trBalance);
				pst.setString(13, picpath);
				
				int count=pst.executeUpdate();
				
				getMsg(count+" Admission Successfully With TraineeId : "+traineeId);
				updateBooked();
				doFetchBatch();
				doFetchDetails();
				
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		
    	
    	

    }
    
    void updateBooked()
    {
    	try {
    		
			pst=con.prepareStatement("update batches set booked=booked+1 where batch=?");
			pst.setString(1, comboTech.getEditor().getText());
			pst.executeUpdate();
		}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void updateBookedDecrement(String tech)
    {
    	try {
    		
			pst=con.prepareStatement("update batches set booked=booked-1 where batch=? && booked>0");
			pst.setString(1, tech);
			pst.executeUpdate();
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void getMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Important Message");
    	alert.setContentText(msg);
    	alert.show();
    }

    
    void doFetchBatch()
    {
    	try {
    		
			pst=con.prepareStatement("select distinct batch from batches");
			
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String tech=tableInMem.getString("batch");
				comboTech.getItems().add(tech);
			}
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    void doFetchCollege()
    {
    	try {
			pst=con.prepareStatement("select distinct college from trainees");
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String trcollege=tableInMem.getString("college");
				comboCollege.getItems().add(trcollege);
			}
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    void doFetchBranch()
    {
    	try {
			pst=con.prepareStatement("select distinct branch from trainees");
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String trbranch=tableInMem.getString("branch");
				comboBranch.getItems().add(trbranch);
			}
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    void doFetchSem(){
    	
    	try {
    		
			pst=con.prepareStatement("select distinct sem from trainees");
			ResultSet tableInMem=pst.executeQuery();
			
			while(tableInMem.next())
			{
				String trSem=tableInMem.getString("sem");
				comboSem.getItems().add(trSem);
				
			}
			
		}
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
    
    void doFetchDetails()
    {
    	doFetchCollege();
   	    doFetchBranch();
   	    doFetchSem();
    }

    Connection con;
    @FXML
    void initialize() {
        
    	 con=ConnectToMysql.doConnect();
    	 
    	 doFetchBatch();
    	 doFetchDetails();
    	 

    }

}
