package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Mensalidade;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class MensalidadeBO extends BaseBO<Mensalidade> {

	public Mensalidade incluir(Mensalidade mensalidade) throws NegocioException {
		return super.incluirComValidacao(mensalidade);
	}

	public Mensalidade alterar(Mensalidade mensalidade) throws NegocioException {

		return super.alterarComValidacao(mensalidade);
	}

	public void excluir(Mensalidade mensalidade) throws NegocioException {
		super.excluirComValidacao(mensalidade);
	}

	public List<Mensalidade> buscarTodos() throws NegocioException {
		return super.buscarTodos(Mensalidade.class);
	}
}
