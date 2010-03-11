package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Proprietario;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class ProprietarioBO extends BaseBO<Proprietario> {

	public Proprietario incluir(Proprietario proprietario) throws NegocioException {
		return super.incluirComValidacao(proprietario);
	}

	public Proprietario alterar(Proprietario proprietario) throws NegocioException {
		return super.alterarComValidacao(proprietario);
	}

	public void excluir(Proprietario proprietario) throws NegocioException {
		super.excluirComValidacao(proprietario);
	}

	public List<Proprietario> buscarTodos() throws NegocioException {
		return super.buscarTodos(Proprietario.class);
	}
}
