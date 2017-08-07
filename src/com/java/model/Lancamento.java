package com.java.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lancamento {
	
	private Integer id;
	private Integer empresa;
	private String numero_nf;
	private Date data_lancamento;
	private Date data_emissao;
	private Integer conta_credito;
	private Integer conta_debito;
	private String valor;
	private String historico;
	
	private Empresa empresaObj = null;
	private Planocontas contaCreditoObj = null;
	private Planocontas contaDebitoObj = null;
	
	public Lancamento() {
		super();
	}	
	
	public Lancamento(Integer id, Integer empresa, String numero_nf, Date data_lancamento, Date data_emissao,
			Integer conta_credito, Integer conta_debito, String valor, String historico, Empresa empresaObj,
			Planocontas contaCreditoObj, Planocontas contaDebitoObj) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.numero_nf = numero_nf;
		this.data_lancamento = data_lancamento;
		this.data_emissao = data_emissao;
		this.conta_credito = conta_credito;
		this.conta_debito = conta_debito;
		this.valor = valor;
		this.historico = historico;
		this.empresaObj = empresaObj;
		this.contaCreditoObj = contaCreditoObj;
		this.contaDebitoObj = contaDebitoObj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public String getNumero_nf() {
		return numero_nf;
	}

	public void setNumero_nf(String numero_nf) {
		this.numero_nf = numero_nf;
	}

	public Date getData_lancamento() {
		return data_lancamento;
	}

	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public Integer getConta_credito() {
		return conta_credito;
	}

	public void setConta_credito(Integer conta_credito) {
		this.conta_credito = conta_credito;
	}

	public Integer getConta_debito() {
		return conta_debito;
	}

	public void setConta_debito(Integer conta_debito) {
		this.conta_debito = conta_debito;
	}	

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	public String getDataLancamentoStr(){
		
		if(data_lancamento != null){			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(data_lancamento);			
		}
		
		return "";
		
	}
	
	public String getDataEmissao(){
		if(data_emissao != null){			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(data_emissao);			
		}
		
		return "";
	}

	public Empresa getEmpresaObj() {
		return empresaObj;
	}

	public void setEmpresaObj(Empresa empresaObj) {
		this.empresaObj = empresaObj;
	}

	public Planocontas getContaCreditoObj() {
		return contaCreditoObj;
	}

	public void setContaCreditoObj(Planocontas contaCreditoObj) {
		this.contaCreditoObj = contaCreditoObj;
	}

	public Planocontas getContaDebitoObj() {
		return contaDebitoObj;
	}

	public void setContaDebitoObj(Planocontas contaDebitoObj) {
		this.contaDebitoObj = contaDebitoObj;
	}
	
	
	
}
