package collector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class feeCollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBal;

    @FXML
    private TextField txtBatchTime;

    @FXML
    private TextField txtPaid;

    @FXML
    private TextField txtReceived;

    @FXML
    private TextField txtTech;

    @FXML
    private TextField txtTotalFee;

    @FXML
    private TextField txtid;
    
    void getMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Important Message");
    	alert.setContentText(msg);
    	alert.show();
    }

    
    PreparedStatement pst;
    @FXML
    void doFetchDetails(ActionEvent event) {
    	
    	try {
    		
			pst=con.prepareStatement("select technology,batchtime,totalfee,advancefee,balance from trainees where tid=?");
			pst.setString(1, txtid.getText());
			ResultSet tableInMem=pst.executeQuery();
			
			boolean jasoos=false;
			
			while(tableInMem.next())
			{
				jasoos=true;
				
				String tech=tableInMem.getString("technology");
				String batchtime=tableInMem.getString("batchtime");
				int fee=tableInMem.getInt("totalfee");
				int advfee=tableInMem.getInt("advancefee");
				int bal=tableInMem.getInt("balance");
				
				txtTech.setText(tech);
				txtBatchTime.setText(batchtime);
				txtTotalFee.setText(String.valueOf(fee));
				txtPaid.setText(String.valueOf(advfee));
				txtBal.setText(String.valueOf(bal));
				
				
			}
			
			if(jasoos==false)
				getMsg("Please Enter Valid Trainee Id");
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	try {
    		
			pst=con.prepareStatement("update trainees set advancefee=advancefee+?,balance=balance-? where tid=?");
			pst.setInt(1, Integer.parseInt(txtReceived.getText()));
			pst.setInt(2, Integer.parseInt(txtReceived.getText()));
			pst.setString(3, txtid.getText());
			
			int count=pst.executeUpdate();
			getMsg(count+" Record Updated");
			doFetchDetails(event);
			
			
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    Connection con;
    @FXML
    void initialize() {

    	con=ConnectToMysql.doConnect();

    }

}

