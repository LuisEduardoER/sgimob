package br.com.fsinformatica.projeto.negocio.bo.cadastro;

import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.TipoImovel;
import br.com.fsinformatica.projeto.comum.utils.validacao.Validador;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class TipoImovelBO extends BaseBO<TipoImovel> {

	public TipoImovel incluir(TipoImovel tipoImovel) throws NegocioException {
		Validador.naoNulo(tipoImovel, "TipoImovel inválido!");
		return super.incluirComValidacao(tipoImovel);
	}

	public TipoImovel alterar(TipoImovel tipoImovel) throws NegocioException {
		Validador.naoNulo(tipoImovel, "TipoImovel inválido!");
		return super.alterarComValidacao(tipoImovel);
	}

	public void excluir(TipoImovel tipoImovel) throws NegocioException {
		super.excluirComValidacao(tipoImovel);
	}

	public List<TipoImovel> buscarTodos() throws NegocioException {
		return super.buscarTodos(TipoImovel.class);
	}
}
