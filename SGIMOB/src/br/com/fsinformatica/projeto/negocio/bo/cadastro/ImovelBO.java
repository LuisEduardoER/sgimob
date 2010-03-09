package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Imovel;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class ImovelBO extends BaseBO<Imovel> {

	public Imovel incluir(Imovel imovel) throws NegocioException {
		return super.incluirComValidacao(imovel);
	}

	public Imovel alterar(Imovel imovel) throws NegocioException {
		return super.alterarComValidacao(imovel);
	}

	public void excluir(Imovel imovel) throws NegocioException {
		super.excluirComValidacao(imovel);
	}

	public List<Imovel> buscarTodos() throws NegocioException {
		return super.buscarTodos(Imovel.class);
	}
}
