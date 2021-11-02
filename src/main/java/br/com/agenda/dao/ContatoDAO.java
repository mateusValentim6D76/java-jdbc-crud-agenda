package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	//CRUD
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();
			
			//Criamos o PreparedStatement, para executar uma query
			pstm = conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
