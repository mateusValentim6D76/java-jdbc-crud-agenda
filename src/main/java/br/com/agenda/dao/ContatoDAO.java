package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	// CRUD

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();

			// Criamos o PreparedStatement, para executar uma query
			pstm = conn.prepareStatement(sql);

			// Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Executa a query
			pstm.execute();
			System.out.println("Contato salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";
		List<Contato> contatos = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);
			// Recebendo o que retornou do banco
			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				// Recuperar o id
				contato.setId(rset.getInt("id"));
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				// Recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
}
