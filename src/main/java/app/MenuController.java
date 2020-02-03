package app;

import java.io.IOException;

import javafx.fxml.FXML;

public class MenuController {
	
    @FXML
    private void setCU02View() throws IOException {
        App.setRoot("CU02View");
    }

    @FXML
    private void setCU03View01() throws IOException {
        App.setRoot("CU03View01");
    }
    
    @FXML
    private void setCU03View02() throws IOException {
        App.setRoot("CU03View02");
    }  
    
    
    @FXML
    private void setCU07View() throws IOException {
        App.setRoot("CU07View");
    }   
	
}
