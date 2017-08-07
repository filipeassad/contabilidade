package com.java.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.java.dao.DatabaseHandler;
import com.java.dao.EmpresaManager;
import com.java.dao.LancamentoManager;
import com.java.dao.PlanoContasManager;
import com.java.estaticos.MainEstaticos;
import com.java.model.Empresa;
import com.java.model.Lancamento;
import com.java.model.Planocontas;
import com.java.utilitarios.Mascaras;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LancamentoController implements Initializable{

	@FXML
	private Label titulo;
	
	@FXML
	private ComboBox<Planocontas> cbCtadev;

	@FXML
	private ComboBox<Empresa> cbEmpresa;	
	
	@FXML
	private ComboBox<Planocontas> cbCtacre;
	
	@FXML
	private TextField tfDataLan;
	
	@FXML
	private TextField tfDataNot;
	
	@FXML
	private TextField tfValor;
	
	@FXML
	private TextField tfHistorico;
	
	@FXML
	private TextField tfNumNota;
	
	private List<Empresa> lista;
	private List<Planocontas> lisCta;
	
	private Empresa empresaSelecionada;
	private Planocontas contaDebSelecionada;
	private Planocontas contaCreSelecionada;
	
	
	DatabaseHandler databaseHandler = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private boolean alteracao = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		if(MainEstaticos.getLancamento() == null){
			tfDataLan.setText(dateFormat.format(new Date()));
		}else{
			alteracao = true;
			preencheCampos();
		}
				
		
		try {
			databaseHandler = new DatabaseHandler("jdbc:sqlite:C:/Odacio_Contabil/banco/contabil.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		carregaListas();
		addListeners();
		
	}
	
	private void preencheCampos(){

		titulo.setText("ALTERAÇÃO DE LANÇAMENTOS");
		empresaSelecionada = MainEstaticos.getLancamento().getEmpresaObj();
		contaDebSelecionada = MainEstaticos.getLancamento().getContaDebitoObj();
		contaCreSelecionada = MainEstaticos.getLancamento().getContaCreditoObj();
		cbEmpresa.getSelectionModel().select(MainEstaticos.getLancamento().getEmpresaObj());
		cbCtadev.getSelectionModel().select(MainEstaticos.getLancamento().getContaDebitoObj());
		cbCtacre.getSelectionModel().select(MainEstaticos.getLancamento().getContaCreditoObj());
		tfDataLan.setText(MainEstaticos.getLancamento().getDataLancamentoStr());
		tfDataNot.setText(MainEstaticos.getLancamento().getDataEmissao());
		tfValor.setText(MainEstaticos.getLancamento().getValor());
		tfNumNota.setText(MainEstaticos.getLancamento().getNumero_nf());
		tfHistorico.setText(MainEstaticos.getLancamento().getHistorico());
	}
	
	public void cadastrar(){
		
		if(empresaSelecionada == null){
			JOptionPane.showMessageDialog(null, "Selecione uma empresa.");
			return;
		}
		
		if(tfNumNota.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Informe o numero da nota.");
			return;
		}
		
		if(tfDataLan.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Informe a data do lançamento.");
			return;
		}
		
		if(tfDataNot.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Informe a data da nota fiscal.");
			return;
		}
		
		if(contaDebSelecionada == null){
			JOptionPane.showMessageDialog(null, "Selecione uma conta devedora.");
			return;
		}
		
		if(contaCreSelecionada == null){
			JOptionPane.showMessageDialog(null, "Selecione uma conta credora.");
			return;
		}
		
		if(tfValor.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Informe o valor.");
			return;
		}
		
		String valor = tfValor.getText();
		
		Lancamento lancamento = new Lancamento();
		lancamento.setEmpresa(empresaSelecionada.getId());
		lancamento.setNumero_nf(tfNumNota.getText());
		lancamento.setConta_credito(contaCreSelecionada.getId());
		lancamento.setConta_debito(contaDebSelecionada.getId());
		try {
			lancamento.setData_lancamento(dateFormat.parse(tfDataLan.getText()));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data do lançamento inválida.");
			e.printStackTrace();
			return;
		}
		
		try {
			lancamento.setData_emissao(dateFormat.parse(tfDataNot.getText()));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data do nota fiscal inválida.");
			e.printStackTrace();
			return;
		}		
		
		lancamento.setValor(valor);
		lancamento.setHistorico(tfHistorico.getText());
		
		LancamentoManager lancamentoManager = new LancamentoManager(databaseHandler.getConnection());
		
		if(alteracao){
			lancamento.setId(MainEstaticos.getLancamento().getId());
			if(lancamentoManager.alteraLancamento(lancamento)){
				JOptionPane.showMessageDialog(null, "Lançamento Alterado com Sucesso!");
				//limpar();
				MainEstaticos.setLancamento(null);
				MainEstaticos.getMainInterface().voltarParaConsulta();
			}else{
				JOptionPane.showMessageDialog(null, "Erro ao Alterado o Lançamento!");
			}
		}else{
			if(lancamentoManager.insertLancamento(lancamento)){
				JOptionPane.showMessageDialog(null, "Lançamento Cadastrado com Sucesso!");
				limpar();
			}else{
				JOptionPane.showMessageDialog(null, "Erro ao Cadastrar o Lançamento!");
			}
		}
		
		
	}
	
	private void limpar(){
		
		empresaSelecionada = null;
		contaCreSelecionada = null;
		contaDebSelecionada = null;
		
		cbCtacre.getSelectionModel().clearSelection();
		cbCtadev.getSelectionModel().clearSelection();
		cbEmpresa.getSelectionModel().clearSelection();
		
		tfDataLan.setText(dateFormat.format(new Date())); 
		tfDataNot.setText("");
		tfHistorico.setText("");
		tfValor.setText("");
		tfNumNota.setText("");
		
	}
	
	private void carregaListas(){
		lista = new ArrayList<>();
		
		if(databaseHandler != null){
			EmpresaManager empresaManager = new EmpresaManager(databaseHandler.getStatement());
			lista = empresaManager.getEmpresas();
		}
		
		if(databaseHandler != null){
			PlanoContasManager contasManager = new PlanoContasManager(databaseHandler.getStatement());
			lisCta = contasManager.getPlanoContas();
		}
		
		
		cbEmpresa.getItems().clear();
		cbEmpresa.getItems().addAll(lista);
		
		cbCtacre.getItems().clear();
		cbCtacre.getItems().addAll(lisCta);
		
		cbCtadev.getItems().clear();
		cbCtadev.getItems().addAll(lisCta);		

		System.out.println("Carregou ok!");
		acaoCombo();
	}
	
	private void acaoCombo(){
		
		cbEmpresa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Empresa>() {
			@Override
			public void changed(ObservableValue<? extends Empresa> observable, Empresa oldValue, Empresa newValue) {
				//empresaSelecionada = newValue;		
				int posicao = cbEmpresa.getSelectionModel().getSelectedIndex();
				//empresaSelecionada = cbEmpresa.getItems().get(posicao);
				empresaSelecionada = lista.get(posicao);
			}
		});
		
		cbCtacre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Planocontas>() {
			@Override
			public void changed(ObservableValue<? extends Planocontas> observable, Planocontas oldValue,
					Planocontas newValue) {
				//contaCreSelecionada = newValue;
				int posicao = cbCtacre.getSelectionModel().getSelectedIndex();
				//contaCreSelecionada = cbCtacre.getItems().get(posicao);
				contaCreSelecionada = lisCta.get(posicao);
			}
		});
		
		cbCtadev.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Planocontas>() {
			@Override
			public void changed(ObservableValue<? extends Planocontas> observable, Planocontas oldValue,
					Planocontas newValue) {
				//contaDebSelecionada = newValue;
				int posicao = cbCtadev.getSelectionModel().getSelectedIndex();
				//contaDebSelecionada = cbCtadev.getItems().get(posicao);
				contaDebSelecionada = lisCta.get(posicao);
			}
		});
		
		
	}
	
	
	/*private void autoCompleteEmpresa(String t1){
		List<Empresa> novos = new ArrayList<>();
    	
    	for(Empresa aux :lista){
    		boolean passou = true;
    		
    		if(t1.length() > aux.getNome_fantasia().length()){
    			passou = false;
    			cbEmpresa.getItems().clear();
    	    	cbEmpresa.getItems().addAll(lista);
				break;
    		}
    		
    		for(int i = 0; i < t1.length(); i++){
    			if(aux.getNome_fantasia().toUpperCase().charAt(i) != t1.toUpperCase().charAt(i)){
    				passou = false;
    				break;
        		}	        			
    		}
    		if(passou)
    			novos.add(aux);
    	}
    	
    	for(Empresa empresa: lista){
    		
    		boolean add = true;
    		for(Empresa empnovo: novos){
    			if(empnovo.getId() == empresa.getId()){
    				add = false;
    				break;
    			}
    		}
    		if(add){
    			novos.add(empresa);
    		}    		
    	}
      
    	cbEmpresa.getItems().clear();
    	cbEmpresa.getItems().addAll(novos);
    	cbEmpresa.show();	
	}
	
	private void autoCompleteCtaDev(String t1){
		List<PlanoContas> novos = new ArrayList<>();
		
    	for(PlanoContas aux :lisCta){
    		boolean passou = true;
    		
    		if(t1.length() > aux.getConta_reduzida().toString().length()){
    			passou = false;
				break;
    		}
    		
    		for(int i = 0; i < t1.length(); i++){
    			if(aux.getConta_reduzida().toString().toUpperCase().charAt(i) != t1.toUpperCase().charAt(i)){
    				passou = false;
    				break;
        		}	        			
    		}
    		if(passou)
    			novos.add(aux);
    	}
      
    	cbCtaDev.getItems().clear();
    	cbCtaDev.getItems().addAll(novos);
    	cbCtaDev.show();	
	}
	
	private void autoCompleteCtaCre(String t1){
		List<PlanoContas> novos = new ArrayList<>();
    	
    	for(PlanoContas aux :lisCta){
    		boolean passou = true;
    		
    		if(t1.length() > aux.getConta_reduzida().toString().length()){
    			passou = false;
				break;
    		}
    		
    		for(int i = 0; i < t1.length(); i++){
    			if(aux.getConta_reduzida().toString().toUpperCase().charAt(i) != t1.toUpperCase().charAt(i)){
    				passou = false;
    				break;
        		}	        			
    		}
    		if(passou)
    			novos.add(aux);
    	}
      
    	cbCtaCre.getItems().clear();
    	cbCtaCre.getItems().addAll(novos);
    	cbCtaCre.show();	
	}*/
	
	private void masacaraData(TextField textField,String novo, String old){
		
		if(novo.length() > 0){
			
			if(novo.length() > 10){
				textField.setText(old);
				return;
			}
			
			if(old.length() < novo.length()){
				if(Character.isDigit(novo.charAt(novo.length() - 1))){
					if(novo.length() == 3){
						textField.setText(old + "/" + novo.charAt(novo.length()-1));
					}
					
					if(novo.length() == 6){
						textField.setText(old + "/" + novo.charAt(novo.length()-1));
					}
					
				}else{
					textField.setText(old);
				}
			}else{
				if(novo.length() == 3){
					textField.setText(novo.substring(0, 2));
				}
				
				if(novo.length() == 6){
					textField.setText(novo.substring(0, 5));
				}
			}
			
		}		
	}
	
	private void addListeners(){
		
		/*cbEmpresa.getEditor().textProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {	        	
	        	autoCompleteEmpresa(t1);      
	        }    
	    });
				
		cbCtaDev.getEditor().textProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {	        	
	        	autoCompleteCtaDev(t1);      
	        }    
	    });
		
		
		
		cbCtaCre.getEditor().textProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {	        	
	        	autoCompleteCtaCre(t1);      
	        }    
	    });*/
		
		tfNumNota.textProperty().addListener(new  ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.length() > 0 ){
					if(!Character.isDigit(newValue.charAt(newValue.length() - 1))){
						tfNumNota.setText(oldValue);
					}
				}
			}
		});
		
		/*tfDataLan.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				masacaraData(tfDataLan, newValue, oldValue);
			}
		});
		
		tfDataNot.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				masacaraData(tfDataNot, newValue, oldValue);				
			}
		});	*/
		
		Mascaras.dateField(tfDataLan);
		Mascaras.dateField(tfDataNot);
		Mascaras.monetaryField(tfValor);
	}	

}
