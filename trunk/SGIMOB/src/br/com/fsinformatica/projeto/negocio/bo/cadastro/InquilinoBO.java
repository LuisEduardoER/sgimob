package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Inquilino;
import br.com.fsinformatica.projeto.comum.utils.validacao.Validador;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class InquilinoBO extends BaseBO<Inquilino> {

	public Inquilino incluir(Inquilino inquilino) throws NegocioException {
		Validador.naoNulo(inquilino, "Inquilino inv�lido!");
		Validador.naoNulo(inquilino.getNome(), "Nome do inquilino n�o pode ser nulo!");
		Validador.naoNulo(inquilino.getTelefone(), "Telefone n�o pode ser nulo!");
		Validador.naoVazio(inquilino.getEndereco(), "Endere�o n�o pode ser vazio!");
		return super.incluirComValidacao(inquilino);
	}

	public Inquilino alterar(Inquilino inquilino) throws NegocioException {
		Validador.naoNulo(inquilino, "Inquilino inv�lido!");
		Validador.naoNulo(inquilino.getNome(), "Nome do inquilino n�o pode ser nulo!");
		Validador.naoNulo(inquilino.getTelefone(), "Telefone n�o pode ser nulo!");
		Validador.naoVazio(inquilino.getEndereco(), "Endere�o n�o pode ser vazio!");
		return super.alterarComValidacao(inquilino);
	}

	public void excluir(Inquilino inquilino) throws NegocioException {
		super.excluirComValidacao(inquilino);
	}

	public List<Inquilino> buscarTodos() throws NegocioException {
		return super.buscarTodos(Inquilino.class);
	}
}
