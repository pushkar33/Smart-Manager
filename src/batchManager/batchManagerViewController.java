package batchManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class batchManagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<batchBean> tableBatch;

    @FXML
    void doFetchAll(ActionEvent event) {
    	
    	
    	ObservableList<batchBean> ary=getAllBatches();
    	tableBatch.setItems(ary); // Mapping array of objects to table

    }
    
    void doAddCols() // adding columns to tableView 
    {
    	TableColumn<batchBean, String> batchCol=new TableColumn<batchBean, String>("Batch");
    	batchCol.setCellValueFactory(new PropertyValueFactory<>("batch"));//same as bean property
    	batchCol.setMinWidth(100);
    	
    	TableColumn<batchBean, String> stDateCol=new TableColumn<batchBean, String>("Start Date");
    	stDateCol.setCellValueFactory(new PropertyValueFactory<>("sdate"));//same as bean property
    	stDateCol.setMinWidth(100);
    	
    	TableColumn<batchBean, String> stTimeCol=new TableColumn<batchBean, String>("Start Time");
    	stTimeCol.setCellValueFactory(new PropertyValueFactory<>("stime"));//same as bean property
    	stTimeCol.setMinWidth(100);
    	
    	TableColumn<batchBean, Integer> totalSeatsCol=new TableColumn<batchBean, Integer>("Total Seats");
    	totalSeatsCol.setCellValueFactory(new PropertyValueFactory<>("tseats"));//same as bean property
    	totalSeatsCol.setMinWidth(100);
    	
    	TableColumn<batchBean, Integer> bookedCol=new TableColumn<batchBean, Integer>("Seats Booked");
    	bookedCol.setCellValueFactory(new PropertyValueFactory<>("booked"));//same as bean property
    	bookedCol.setMinWidth(100);
    	
    	TableColumn<batchBean, Integer> feeCol=new TableColumn<batchBean, Integer>("Total Fee");
    	feeCol.setCellValueFactory(new PropertyValueFactory<>("fee"));//same as bean property
    	feeCol.setMinWidth(100);
    	
    	tableBatch.getColumns().addAll(batchCol,stDateCol,stTimeCol,totalSeatsCol,bookedCol,feeCol);
    }
    
    ObservableList<batchBean> ary=FXCollections.observableArrayList();
    ObservableList<batchBean> getAllBatches()
    {
    	
    	
    	
    	try {
    		
			PreparedStatement pst=con.prepareStatement("select * from batches");
		    ResultSet tableInMem=pst.executeQuery();
		    
		    while(tableInMem.next())
		    {
		    	String batch=tableInMem.getString("batch");
		    	String stDate=tableInMem.getDate("sdate").toString();
		    	String stTime=tableInMem.getString("stime");
		    	int totalseats=tableInMem.getInt("tseats");
		    	int bookedSeats=tableInMem.getInt("booked");
		    	int tfee=tableInMem.getInt("fee");
		    	
		    	batchBean obj=new batchBean(batch,stDate,stTime,totalseats,bookedSeats,tfee);
		    	ary.add(obj);
		    	
		    }
		    
		    
		    
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	return ary;
    	
    }
    
    @FXML
    void doExportToExcel(ActionEvent event)
    {
    	try {
    		writeExcel();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void writeExcel() throws Exception
    {
    	Writer writer=null;
    	try {
    		
    		File file=new File("batches.csv");
        	writer=new BufferedWriter(new FileWriter(file));
        	String text="Batch,Start Date,Start Time,Total Seats,Booked Seats,Total Fee";
        	writer.write(text);
        	for(batchBean b:ary)
        	{
        		text=b.getBatch()+","+b.getSdate()+","+b.getStime()+","+b.getTseats()+","+b.getBooked()+","+b.getFee();
        		writer.write(text);
        	}
    		
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	finally {
	           
            writer.flush();
             writer.close();
        }
    	
    }

    Connection con;
    @FXML
    void initialize() {
       
    	con=ConnectToMysql.doConnect();
        doAddCols();
    	
    }

}
