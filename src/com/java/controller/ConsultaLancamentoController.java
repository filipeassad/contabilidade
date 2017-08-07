package com.java.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.java.dao.DatabaseHandler;
import com.java.dao.LancamentoManager;
import com.java.estaticos.MainEstaticos;
import com.java.interfaces.LancamentosInterface;
import com.java.listcell.LancamentoCell;
import com.java.model.Lancamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ConsultaLancamentoController implements Initializable, LancamentosInterface {
	
	@FXML
	private ListView<Lancamento> listaLancamento;
	
	private ObservableList<Lancamento> listaLan = FXCollections.observableArrayList();
	
	private DatabaseHandler database;
	private LancamentoManager lancametoManager;
	
	private LancamentosInterface lancaInter = this;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			database = new DatabaseHandler("jdbc:sqlite:C:/Odacio_Contabil/banco/contabil.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lancametoManager = new LancamentoManager(database.getConnection());
		
		listaLan.setAll(lancametoManager.getLancamentos(null));
		listaLancamento.setItems(listaLan);
		
		listaLancamento.setCellFactory(new Callback<ListView<Lancamento>, ListCell<Lancamento>>() {
			
			@Override
			public ListCell<Lancamento> call(ListView<Lancamento> param) {
				return new LancamentoCell(lancaInter);
			}
			
		});
		
	}


	@Override
	public void alteraLancamento(Lancamento lancamento) {	
		if (JOptionPane.showConfirmDialog(null, "Você deseja alterar esse lançamento ?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {			
			MainEstaticos.getMainInterface().voltarParaOCadastro(lancamento);		
		}
	}


	@Override
	public void excluiLancamento(Lancamento lancamento) {
		
		
		if (JOptionPane.showConfirmDialog(null, "Você deseja excluir esse lançamento ?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if(lancametoManager.deleteLancamento(lancamento)){
				
				JOptionPane.showMessageDialog(null, "Lançamento excluído com sucesso.");
				lancametoManager = new LancamentoManager(database.getConnection());
				listaLan =  FXCollections.observableArrayList();
				listaLan.setAll(lancametoManager.getLancamentos(null));
				listaLancamento.setItems(listaLan);
				
				listaLancamento.setCellFactory(new Callback<ListView<Lancamento>, ListCell<Lancamento>>() {
					
					@Override
					public ListCell<Lancamento> call(ListView<Lancamento> param) {
						return new LancamentoCell(lancaInter);
					}
					
				});			
			}else{
				JOptionPane.showMessageDialog(null, "Não foi possível excluir o lançamento.");
			}
		}
		
		
		
		
	}

}
