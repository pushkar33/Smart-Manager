package welcome;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void openAbout(ActionEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/about/aboutDevView.fxml"));
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

    @FXML
    void openLogin(ActionEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/login/LoginView.fxml"));
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

    @FXML
    void initialize() {

    }

}
