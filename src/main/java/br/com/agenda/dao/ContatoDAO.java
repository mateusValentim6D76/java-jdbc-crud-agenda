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
			// Criar conex?o com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();

			// Criamos o PreparedStatement, para executar uma query
			pstm = conn.prepareStatement(sql);

			// Adicionar os valores que s?o esperados pela query
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

	public void update(Contato contatos) {

		String sql = "UPDATE contatos SET nome = ?, idade = ? , datacadastro = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conex?o com o banco
			conn = ConnectionFactory.createConnectionToMySql();

			// Criar classe para executar a query
			pstm = conn.prepareStatement(sql);

			// Add valores para atualizar
			pstm.setString(1, contatos.getNome());
			pstm.setInt(2, contatos.getIdade());
			pstm.setDate(3, new Date(contatos.getDataCadastro().getTime()));

			// Qual ID do registro deseja atualizar ?
			pstm.setInt(4, contatos.getId());

			// Executar a query
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	}

	public void deleteById(int id) {
		String sql = "DELETE from contatos WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstm != null) {
					pstm.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
