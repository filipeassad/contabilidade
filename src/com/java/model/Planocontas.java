package com.java.model;

public class Planocontas {
	
	private Integer id;
	private Integer conta_reduzida;
	private String classificacao;
	private String nome;
	
	public Planocontas() {
		super();
	}		

	public Planocontas(Integer id, Integer conta_reduzida, String classificacao, String nome) {
		super();
		this.id = id;
		this.conta_reduzida = conta_reduzida;
		this.classificacao = classificacao;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConta_reduzida() {
		return conta_reduzida;
	}

	public void setConta_reduzida(Integer conta_reduzida) {
		this.conta_reduzida = conta_reduzida;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return conta_reduzida + " - " + nome ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planocontas other = (Planocontas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
