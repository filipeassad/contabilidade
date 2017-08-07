package com.java.item;

import java.io.IOException;
import java.net.URL;

import com.java.interfaces.LancamentosInterface;
import com.java.model.Lancamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LancamentoItem {
	
	@FXML
	private VBox vBox;
	
	@FXML
	private Label empresa;
	
	@FXML
	private Label numronf;
	
	@FXML
	private Label datalanc;
	
	@FXML
	private Label dataemi;
	
	@FXML
	private Label contacre;
	
	@FXML
	private Label contadeb;
	
	@FXML
	private Label valor;
	
	@FXML
	private Label historico;
	
	private Lancamento lancamento;
	
	private LancamentosInterface lancamentosInterface;
	
	public LancamentoItem(LancamentosInterface lancamentosInterface){
		
		super();
		this.lancamentosInterface = lancamentosInterface;
		
		URL arquivoFXML = getClass().getResource("/com/gui/item/LancamentoCell.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(arquivoFXML);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public VBox getBox(){
		return vBox;
	}
	
	public void setInfo(Lancamento lancamento){
		
		this.lancamento = lancamento;
		
		empresa.setText(lancamento.getEmpresaObj().getNome_fantasia());		
		numronf.setText(lancamento.getNumero_nf());
		datalanc.setText(lancamento.getDataLancamentoStr());
		dataemi.setText(lancamento.getDataEmissao());
		contacre.setText(lancamento.getContaCreditoObj().getConta_reduzida() + " - " + lancamento.getContaCreditoObj().getNome());
		contadeb.setText(lancamento.getContaDebitoObj().getConta_reduzida() + " - " + lancamento.getContaDebitoObj().getNome());
		valor.setText(lancamento.getValor()+"");
		historico.setText(lancamento.getHistorico());		
		
	}
	
	@FXML
	private void excluir(ActionEvent actionEvent){
		lancamentosInterface.excluiLancamento(lancamento);
	}
	
	@FXML
	private void alterar(ActionEvent actionEvent){
		lancamentosInterface.alteraLancamento(lancamento);
	}
	

}
