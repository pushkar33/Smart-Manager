package Batch;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class BatchMasterViewController extends Application
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBatch;

    @FXML
    private ComboBox<String> comboTime;

    @FXML
    private DatePicker dos;

    @FXML
    private ImageView picBatch;

    @FXML
    private TextField txtTotalSeats;

    @FXML
    private TextField txtFee;
    
    
    
   public void start(Stage stage)
  {
	FileChooser filechooser=new FileChooser(); 
   	
   	File selectedFile=filechooser.showOpenDialog(stage);
   	
   	if (selectedFile != null) {
   	
   		// creating new image instance using selected file pathname
   		Image img=new Image(selectedFile.toURI().toString());
   		
   		// Setting img to picview 
   		picBatch.setImage(img);
   		
   	}
}
    
    @FXML
    void doBrowse(ActionEvent event) {
    	
    	Stage stage=new Stage();
    	start(stage);

    }

    @FXML
    void doChangePic(MouseEvent event) {
    	
    	

    }

    @FXML
    void doDelete(ActionEvent event) {
    	
    	try {
    		
    		pst=con.prepareStatement("delete from batches where batch=?");
        	pst.setString(1,comboBatch.getEditor().getText());
        	
        	int count=pst.executeUpdate();
        	
        	getMsg(count+" Records Deleted Sucxcessfully");
    		
    	}
    	catch(SQLException e) {
    		
    		e.printStackTrace();
    		
    	}
    	

    }

    PreparedStatement pst;
    int booked=0;
    @FXML
    void doSave(ActionEvent event) {
    	
    try {
    	
		pst=con.prepareStatement("insert into batches values(?,?,?,?,?,?)");
		pst.setString(1, comboBatch.getEditor().getText());
		
		LocalDate startDate=dos.getValue();
		pst.setDate(2,Date.valueOf(startDate));
		
		pst.setString(3, comboTime.getEditor().getText());
		pst.setInt(4, Integer.parseInt(txtTotalSeats.getText()));
		pst.setInt(5,booked);
		pst.setInt(6, Integer.parseInt(txtFee.getText()));
		
		int count=pst.executeUpdate();
		getMsg(count+" Records Saved Successfully");
		
	} 
    catch (SQLException e) {
		
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

    @FXML
    void doSearch(ActionEvent event) {
    	
    	try {
    		
			pst=con.prepareStatement("select * from batches where batch=?");
			pst.setString(1,comboBatch.getEditor().getText());
			
			ResultSet tableInMem=pst.executeQuery();
			
			boolean jasoos=false;
			
			while(tableInMem.next())
			{
				jasoos=true;
				
				Date stdate=tableInMem.getDate("sdate");
				String time=tableInMem.getString("stime");
				int totalSeats=tableInMem.getInt("tseats");
				int fee=tableInMem.getInt("fee");
				
				dos.setValue(stdate.toLocalDate());
				comboTime.setValue(time);
				txtTotalSeats.setText(String.valueOf(totalSeats));
				txtFee.setText(String.valueOf(fee));
				
				
				
			}
			
			if(jasoos==false)
				getMsg("Batch Doesn't Exist");
		}
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	 try {
    	    	
    			pst=con.prepareStatement("update batches set sdate=?,stime=?,tseats=?,fee=?");
    			
    			
    			LocalDate startDate=dos.getValue();
    			pst.setDate(1,Date.valueOf(startDate));
    			
    			pst.setString(2, comboTime.getEditor().getText());
    			pst.setInt(3, Integer.parseInt(txtTotalSeats.getText()));
    			//pst.setInt(5,booked);
    			pst.setInt(4, Integer.parseInt(txtFee.getText()));
    			
    			int count=pst.executeUpdate();
    			
    			if(count==0)
    				getMsg("Please Enter Valid Batch");
    			else
    			    getMsg(count+" Records Updated Successfully");
    			
    		} 
    	    catch (SQLException e) {
    			
    			e.printStackTrace();
    		}    	

    }
    	    

    

    Connection con;
    @FXML
    void initialize() {
    
    con=ConnectToMysql.doConnect();
    
    ArrayList<String> ary=new ArrayList<String>(Arrays.asList("C++","Java","NodeJs","Mern Stack","Dsa"));
    comboBatch.getItems().addAll(ary);
    
    ArrayList<String> timeary=new ArrayList<String>(Arrays.asList("8am-10am","10am-12pm","12pm-2pm","3pm-5pm","5pm-7pm"));
    comboTime.getItems().addAll(timeary);
    
    }

}
