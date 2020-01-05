package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class CU03Controller01 {
    
    @FXML
    private void switchToCU0301() throws IOException {
        App.setRoot("CU03View01");
    }

    
	public void a() {
		System.out.println("GHOLA\n");
	}

	
}
