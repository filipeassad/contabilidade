package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	
	private Connection connection = null;
	private Statement statement = null;
	private String caminhoDB = "";
	
	public DatabaseHandler(String caminhoDB) throws ClassNotFoundException{
		
		Class.forName("org.sqlite.JDBC");		
		
		try {
			this.caminhoDB = caminhoDB;
			connection = DriverManager.getConnection(caminhoDB);
			statement = connection.createStatement();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Statement getStatement(){
		try {
			statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Connection getConnection() {
		try {			
			connection = DriverManager.getConnection(caminhoDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(){
		try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	}

}
