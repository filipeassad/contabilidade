package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Planocontas;

public class PlanoContasManager {

	private Statement statement= null;

	public PlanoContasManager(Statement statement) {
		super();
		this.statement = statement;
	}
	
	public List<Planocontas> getPlanoContas(){
		
		try {
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("select * from PlanoContas");
			if(rs != null){
				List<Planocontas> listaPlan = new ArrayList<Planocontas>();
				
				while(rs.next()){
					Planocontas contas = new Planocontas();
					
					contas.setId(rs.getInt("id"));
					contas.setConta_reduzida(rs.getInt("conta_reduzida"));
					contas.setClassificacao(rs.getString("classificacao"));
					contas.setNome(rs.getString("nome"));
					
					listaPlan.add(contas);					
					
				}
				
				return listaPlan;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
