module InstituteManagement {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Batch to javafx.graphics, javafx.fxml;
	opens Admission to javafx.graphics, javafx.fxml;
	opens batchManager to javafx.graphics, javafx.fxml,javafx.base;
	opens googler to javafx.graphics, javafx.fxml,javafx.base;
	opens collector to javafx.graphics, javafx.fxml;
	opens status to javafx.graphics, javafx.fxml;
	opens dashboard to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens about to javafx.graphics, javafx.fxml;
	opens welcome to javafx.graphics, javafx.fxml;
}
