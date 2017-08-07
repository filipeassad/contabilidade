package com.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.java.estaticos.MainEstaticos;
import com.java.interfaces.MainInterface;
import com.java.model.Lancamento;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController implements Initializable, MainInterface{
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private VBox vBox;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//menuBar.setFocusTraversable(true);
		MainEstaticos.setMainInterface(this);
		URL arquivoFXML = getClass().getResource("/com/gui/view/Lancamento.fxml");
		vBox.getChildren().clear();
		try {
			vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void funcaoTeclado(){
		
	}	
	
	public void consultaLan() throws IOException{
		URL arquivoFXML = getClass().getResource("/com/gui/view/ConsultaLancamento.fxml");
		vBox.getChildren().clear();
		vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
	}
	
	public void cadastroLan() throws IOException{
		URL arquivoFXML = getClass().getResource("/com/gui/view/Lancamento.fxml");
		MainEstaticos.setLancamento(null);
		vBox.getChildren().clear();
		vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
	}
	
	public void bancoDados() throws IOException{
		URL arquivoFXML = getClass().getResource("/com/gui/view/BancoDados.fxml");
		vBox.getChildren().clear();
		vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
	}
	
	public void sair(){		
		Stage stage = (Stage) vBox.getScene().getWindow();
	    stage.close();		
	}
	
	/*public void gerarRelatorio(){
		GerarRelatorio gerarRelatorio = new GerarRelatorio();
		gerarRelatorio.gerarRelatorio();
	}*/

	@Override
	public void voltarParaOCadastro(Lancamento lancamento) {
		MainEstaticos.setLancamento(lancamento);
		URL arquivoFXML = getClass().getResource("/com/gui/view/Lancamento.fxml");
		vBox.getChildren().clear();
		try {
			vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void voltarParaConsulta() {
		URL arquivoFXML = getClass().getResource("/com/gui/view/ConsultaLancamento.fxml");
		vBox.getChildren().clear();
		try {
			vBox.getChildren().add((Node) FXMLLoader.load(arquivoFXML));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
