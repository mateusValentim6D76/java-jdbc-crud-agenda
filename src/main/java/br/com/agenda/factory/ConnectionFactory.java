package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	/*
	 * Conexão com o banco
	 */
	
	public static Connection createConnectionToMySql() throws Exception {
		// Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Cria a conexão com o Banco de Dados
		Connection connection = DriverManager.getConnection(DATABASE_URL,USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws Exception {
		 //Recuperar conexão com o banco de dados
		Connection conn = createConnectionToMySql();
		
		//Testa se a conexão é nula
		if(conn !=null) {
			System.out.println("Conexão obtida com sucesso");
			conn.close();
		}
	}
}
