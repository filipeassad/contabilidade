package com.java.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Empresa;
import com.java.model.Lancamento;
import com.java.model.Planocontas;

public class LancamentoManager {

	private Statement statement = null;
	private Connection connection = null;

	public LancamentoManager(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean insertLancamento(Lancamento lancamento){
		
		try {
						
			/*statement.executeUpdate("INSERT INTO Lancamento (empresa, numero_nf, data_lancamento, data_emissao, conta_credito, conta_debito, valor, historico)" +
                "VALUES (?,?,?,?,?,?,?,?);");*/
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Lancamento (empresa, numero_nf, data_lancamento, data_emissao, conta_credito, conta_debito, valor, historico) VALUES (?,?,?,?,?,?,?,?)");
			//statement.setInt(1, 0);
			//statement.execute("DROP TABLE IF EXISTS charge");
			statement.clearParameters();
			statement.setInt(1, lancamento.getEmpresa());
			statement.setString(2, lancamento.getNumero_nf());
			statement.setDate(3, new java.sql.Date(lancamento.getData_lancamento().getTime()));
			statement.setDate(4, new java.sql.Date(lancamento.getData_emissao().getTime()));
			statement.setInt(5, lancamento.getConta_credito());
			statement.setInt(6, lancamento.getConta_debito());
			statement.setString(7, lancamento.getValor());
			statement.setString(8, lancamento.getHistorico());
			int  resultado = statement.executeUpdate();
			
			//connection.commit();
			
			if(resultado > 0){
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	public List<Lancamento> getLancamentos(Lancamento lanca){
			
		try {
			statement = connection.createStatement();
			
			statement.setQueryTimeout(30);
			
			String sql = "SELECT lan.id, lan.empresa, lan.numero_nf,"
					+ "lan.data_lancamento, lan.data_emissao, lan.conta_credito,"
					+ "lan.conta_debito, lan.valor, lan.historico,"
					+ "emp.id, emp.cnpj, emp.razao_social, emp.nome_fantasia,"
					+ "emp.endereco, emp.complemento, emp.numero,"
					+ "emp.cep, emp.telefone, emp.email,"
					+ "ctc.id, ctc.conta_reduzida, ctc.classificacao, ctc.nome,"
					+ "ctd.id, ctd.conta_reduzida, ctd.classificacao, ctd.nome FROM Lancamento lan "
					+ "LEFT JOIN Empresa emp ON emp.id = lan.empresa "
					+ "LEFT JOIN PlanoContas ctc ON ctc.id = lan.conta_credito "
					+ "LEFT JOIN PlanoContas ctd ON ctd.id = lan.conta_debito ";
			
			if(lanca != null){
				boolean comAND = false;
				
				sql = sql + "WHERE ";
				
				if(lanca.getEmpresaObj() != null 
						&& lanca.getEmpresaObj().getNome_fantasia() != null 
						&& lanca.getEmpresaObj().getNome_fantasia().trim().equals("")){
					
					sql = sql + "emp.nome like " + lanca.getEmpresaObj().getNome_fantasia() + "%";
					comAND = true;
				}
				
				if(lanca.getNumero_nf() != null && !lanca.getNumero_nf().trim().equals("")){
					if(comAND){
						sql = sql + " AND ";
					}
					sql = sql + "lan.numero_nf like " + lanca.getNumero_nf() + "%";
				}
				
				if(lanca.getDataLancamentoStr() != null && !lanca.getDataLancamentoStr().trim().equals("")){
					if(comAND){
						sql = sql + " AND ";
					}
					sql = sql + "lan.data_lancamento = " +  new java.sql.Date(lanca.getData_emissao().getTime()).toString();
				}
				
				if(lanca.getDataEmissao() != null && !lanca.getDataLancamentoStr().trim().equals("")){
					if(comAND){
						sql = sql + " AND ";
					}
					sql = sql + "lan.data_emissao = " + new java.sql.Date(lanca.getData_emissao().getTime());					
				}
				
				if(lanca.getContaCreditoObj() != null && lanca.getContaCreditoObj().getConta_reduzida() != null){
					if(comAND){
						sql = sql + " AND ";
					}
					
					sql = sql + "ctc.conta_reduzida = " + lanca.getContaCreditoObj().getConta_reduzida();
				}
				
				if(lanca.getContaDebitoObj() != null && lanca.getContaDebitoObj().getConta_reduzida() != null){
					if(comAND){
						sql = sql + " AND ";
					}
					
					sql = sql + "ctd.conta_reduzida = " + lanca.getContaDebitoObj().getConta_reduzida();
				}
				
				if(lanca.getValor() != null){
					if(comAND){
						sql = sql + " AND ";
					}
					sql = sql + "lan.valor = " + lanca.getValor();
				}
				
				if(lanca.getHistorico() != null && !lanca.getHistorico().trim().equals("")){
					if(comAND){
						sql = sql + " AND ";
					}
					sql = sql + "lan.historico = " +  lanca.getHistorico();
				}
				
			}
			
			ResultSet rs = statement.executeQuery(sql);
			if(rs != null){
				List<Lancamento> listaLan = new ArrayList<>();
				while(rs.next()){
					
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt(10));
					empresa.setCnpj(rs.getString(11));
					empresa.setRazao_social(rs.getString(12));
					empresa.setNome_fantasia(rs.getString(13));
					empresa.setEndereco(rs.getString(14));
					empresa.setComplemento(rs.getString(15));
					empresa.setNumero(rs.getString(16));
					empresa.setCep(rs.getString(17));
					empresa.setTelefone(rs.getString(18));
					empresa.setEmail(rs.getString(19));
					
					Planocontas contac = new Planocontas();
					
					contac.setId(rs.getInt(20));
					contac.setConta_reduzida(rs.getInt(21));
					contac.setClassificacao(rs.getString(22));
					contac.setNome(rs.getString(23));
					
					
					Planocontas contad = new Planocontas();
					
					contad.setId(rs.getInt(24));
					contad.setConta_reduzida(rs.getInt(25));
					contad.setClassificacao(rs.getString(26));
					contad.setNome(rs.getString(27));
					
					Lancamento lancamento = new Lancamento();
					
					lancamento.setId(rs.getInt(1));
					lancamento.setEmpresa(rs.getInt(2));
					lancamento.setNumero_nf(rs.getString(3));
					lancamento.setData_lancamento(rs.getDate(4));
					lancamento.setData_emissao(rs.getDate(5));
					lancamento.setConta_credito(rs.getInt(6));
					lancamento.setConta_debito(rs.getInt(7));
					lancamento.setValor(rs.getString(8));
					lancamento.setHistorico(rs.getString(9));
					
					lancamento.setEmpresaObj(empresa);
					lancamento.setContaCreditoObj(contac);
					lancamento.setContaDebitoObj(contad);				
					
					listaLan.add(lancamento);
			        
			    }
				return listaLan;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // set timeout to 30 sec.	
		
		
		return null;
	}
	
	
	public boolean deleteLancamento(Lancamento lancamento){
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Lancamento WHERE id = ? ;");
			statement.clearParameters();
			statement.setInt(1, lancamento.getId());
			
			int  resultado = statement.executeUpdate();
			
			//connection.commit();
			
			if(resultado > 0){
				return true;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	public boolean alteraLancamento(Lancamento lancamento){
		
		try {
			
			/*statement.executeUpdate("UPDATE Lancamento SET empresa = ?, numero_nf = ?, data_lancamento = ?, data_emissao = ?, conta_credito = ?, conta_debito = ?, valor = ?, historico = ?" +
                "VALUES (?,?,?,?,?,?,?,?);");*/
			PreparedStatement statement = connection.prepareStatement("UPDATE Lancamento "
																	+ "SET empresa = ?, numero_nf = ?, data_lancamento = ?, "
																	+ "data_emissao = ?, conta_credito = ?, conta_debito = ?, "
																	+ "valor = ?, historico = ?"
																	+ "WHERE id = ?");
			//statement.setInt(1, 0);
			//statement.execute("DROP TABLE IF EXISTS charge");
			statement.clearParameters();
			statement.setInt(1, lancamento.getEmpresa());
			statement.setString(2, lancamento.getNumero_nf());
			statement.setDate(3, new java.sql.Date(lancamento.getData_lancamento().getTime()));
			statement.setDate(4, new java.sql.Date(lancamento.getData_emissao().getTime()));
			statement.setInt(5, lancamento.getConta_credito());
			statement.setInt(6, lancamento.getConta_debito());
			statement.setString(7, lancamento.getValor());
			statement.setString(8, lancamento.getHistorico());
			statement.setInt(9, lancamento.getId());
			int  resultado = statement.executeUpdate();
			
			//connection.commit();
			
			if(resultado > 0){
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
		
	}
	
	
}
