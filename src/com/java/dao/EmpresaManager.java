package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Empresa;
import com.java.model.Planocontas;

public class EmpresaManager {
	
	private Statement statement= null;

	public EmpresaManager(Statement statement) {
		super();
		this.statement = statement;
	}

	public List<Empresa> getEmpresas(){
		
		try {
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("select * from Empresa");
			if(rs != null){
				List<Empresa> listaEmp = new ArrayList<Empresa>();
				while(rs.next()){
					
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt("id"));
					empresa.setCnpj(rs.getString("cnpj"));
					empresa.setRazao_social(rs.getString("razao_social"));
					empresa.setNome_fantasia(rs.getString("nome_fantasia"));
					empresa.setEndereco(rs.getString("endereco"));
					empresa.setComplemento(rs.getString("complemento"));
					empresa.setNumero(rs.getString("numero"));
					empresa.setCep(rs.getString("cep"));
					empresa.setTelefone(rs.getString("telefone"));
					empresa.setEmail(rs.getString("email"));
					
					
					listaEmp.add(empresa);
					
			        //System.out.println("name = " + rs.getString("name"));
			        //System.out.println("id = " + rs.getInt("id"));
			    }
				
				return listaEmp;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // set timeout to 30 sec.
		
		return null;
	}

}
