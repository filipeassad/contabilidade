package com.java.app;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage palco) {
		
		URL arquivoFXML = getClass().getResource("/com/gui/view/Main.fxml");
		Parent fxmlParent;
		
		try {
			
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			palco.setScene(new Scene(fxmlParent, 800, 400));
			palco.setTitle("Contabilidade");
			palco.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
