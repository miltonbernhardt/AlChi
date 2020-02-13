package app;

import javafx.fxml.FXML;

public class MenuController  {
	
    @FXML
    private void setCU01View() {
        new CU01Controller();
		CU01Controller.get().setView();
    }
	
    @FXML
    private void setCU02View() {
        new CU02Controller();
		CU02Controller.get().setView();
    }

    @FXML
    private void setCU03View01() {
        new CU03Controller01();
		CU03Controller01.get().setView();
    } 
    
    @FXML
    private void setCU04View() {
        new CU04Controller();
		CU04Controller.get().setView();
    }  
    
    @FXML
    private void setCU05View() {
        new CU05Controller();
		CU05Controller.get().setView(null);
    }
    
    @FXML
    private void setCU06View() {
        new CU06Controller();
		CU06Controller.get().setView();
    } 
        
    @FXML
    private void setCU07View() {
        new CU07Controller();
		CU07Controller.get().setView();
    }  
    
    @FXML
    private void setCU08View() {
        new CU08Controller();
		CU08Controller.get().setView();
    }   
    
    @FXML
    private void setCU09View() {
        new CU09Controller();
		CU09Controller.get().setView();
    }

    @FXML
    private void setCU10View() {
        new CU10Controller();
		CU10Controller.get().setView();
    }
    
    @FXML
    private void setCU11View() {
        new CU11Controller();
		CU11Controller.get().setView();
    }  
    
    @FXML
    private void setCU12View() {
        new CU12Controller();
		CU12Controller.get().setView();
    }  
    
    @FXML
    private void setCU13View() {
        new CU13Controller();
		CU13Controller.get().setView();
    }
    
    @FXML
    private void setCU14View() {
        new CU14Controller();
		CU14Controller.get().setView();
    } 
        
    @FXML
    private void setCU15View() {
        new CU15Controller();
		CU15Controller.get().setView();
    }  
	
}
