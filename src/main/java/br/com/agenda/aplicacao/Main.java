package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Helena Regina");
		contato.setIdade(54);
		contato.setDataCadastro(new Date());
		
		//contatoDao.save(contato);
		
		//Atualizar contato
		Contato c1 = new Contato();
		
		c1.setNome("Lorena Marquês");
		c1.setIdade(33);
		c1.setDataCadastro(new Date());
		c1.setId(3);
		
		//contatoDao.update(c1);
		
		//Deletar o contato por ID
		contatoDao.deleteById(8);
		contatoDao.deleteById(7);
		contatoDao.deleteById(6);
		contatoDao.deleteById(5);
		
		//VISUALIZAÇÃO dos registros do banco de dados ***TODOS***
		
		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome() +", Idade: " + c.getIdade() +", Data do cadastro: "+ c.getDataCadastro());
		}
	}
}
