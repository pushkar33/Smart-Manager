package dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class dashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doOpenFeeStat(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/status/feeStatusView.fxml"));
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
    void doOpenFeecollector(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/collector/feeCollectorView.fxml"));
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
    void doOpenStuGoogler(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/googler/traineeGooglerView.fxml"));
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
    void doOpenadmission(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/Admission/NewAddmissionView.fxml"));
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
    void doOpenbatch(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/Batch/BatchMasterView.fxml"));
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
    void doOpenbatchManager(MouseEvent event) {
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getResource("/batchManager/batchManagerView.fxml"));
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
