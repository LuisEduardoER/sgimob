package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Servico;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class ServicoBO extends BaseBO<Servico> {

	public Servico incluir(Servico servico) throws NegocioException {
		return super.incluirComValidacao(servico);
	}

	public Servico alterar(Servico servico) throws NegocioException {
		return super.alterarComValidacao(servico);
	}

	public void excluir(Servico servico) throws NegocioException {
		super.excluirComValidacao(servico);
	}

	public List<Servico> buscarTodos() throws NegocioException {
		return super.buscarTodos(Servico.class);
	}
}
