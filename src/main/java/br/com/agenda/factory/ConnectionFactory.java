package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	/*
	 * Conex�o com o banco
	 */
	
	public static Connection createConnectionToMySql() throws Exception {
		// Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Cria a conex�o com o Banco de Dados
		Connection connection = DriverManager.getConnection(DATABASE_URL,USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws Exception {
		 //Recuperar conex�o com o banco de dados
		Connection conn = createConnectionToMySql();
		
		//Testa se a conex�o � nula
		if(conn !=null) {
			System.out.println("Conex�o obtida com sucesso");
			conn.close();
		}
	}
}
