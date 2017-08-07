package com.java.model;

public class Empresa {
	
	private Integer id;
	private String cnpj;
	private String razao_social;
	private String nome_fantasia;
	private String endereco;
	private String complemento;
	private String numero;
	private String cep;
	private String telefone;
	private String email;
	
	public Empresa() {
		super();
	}

	public Empresa(Integer id, String cnpj, String razao_social, String nome_fantasia, String endereco,
			String complemento, String numero, String cep, String telefone, String email) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.razao_social = razao_social;
		this.nome_fantasia = nome_fantasia;
		this.endereco = endereco;
		this.complemento = complemento;
		this.numero = numero;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return  nome_fantasia;
	}

}
