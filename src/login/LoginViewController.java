package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUserid;

    @FXML
    void doLogin(ActionEvent event) {
    	
    	if(txtUserid.getText().equals("bceadmin") && txtPass.getText().equals("sst9910"))
    	{
    		openDash();
    	}
    	else
    	{
    		getMsg("Invalid Credentials");
    	}

    }
    
    void openDash()
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/dashboard/dashboardView.fxml"));
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void getMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Error : Invalid User");
    	alert.setContentText(msg);
    	alert.show();
    }


    @FXML
    void initialize() {
        assert txtPass != null : "fx:id=\"txtPass\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert txtUserid != null : "fx:id=\"txtUserid\" was not injected: check your FXML file 'LoginView.fxml'.";

    }

}
