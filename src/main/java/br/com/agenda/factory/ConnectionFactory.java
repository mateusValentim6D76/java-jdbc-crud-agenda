package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	/*
	 * Conexão com o banco
	 */
	public static Connection createConnectionToMySql() throws Exception  {
		//Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//Cria a conexão com o Banco de Dados
		Connection connection = DriverManager.getConnection(USERNAME, PASSWORD, DATABASE_URL);
		
		return connection;
	}
}
