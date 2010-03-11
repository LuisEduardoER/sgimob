package br.com.fsinformatica.projeto.apresentacao;

import java.io.Serializable;
import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public class EntidadeBean<E extends Entidade> {

	BaseBO<E> baseBO = new BaseBO<E>();

	public String incluir(E entidade) {
		try {
			baseBO.incluir(entidade);
			MBeanUtils
			.adicionarMensagemSucesso("Operação de inclusão executada com sucesso.");
			return "sucesso";
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
			return "erro";
		}
	}

	public String alterar(E entidade) {
		try {
			baseBO.alterar(entidade);
			MBeanUtils
			.adicionarMensagemSucesso("Operação de inclusão executada com sucesso.");
			return "sucesso";
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
			return "erro";
		}
	}

	public String excluir(E entidade) {
		try {
			baseBO.excluir(entidade);
			MBeanUtils
			.adicionarMensagemSucesso("Operação de inclusão executada com sucesso.");
			return "sucesso";
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
			return "erro";
		}
	}

	public E recuperar(Class<E> classeEntidade, Serializable id) {
		E retorno = null;
		try {
			retorno = baseBO.buscar(classeEntidade, id);
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
		}
		return retorno;
	}

	public List<E> pesquisarPorNome(Class<E> classeDaEntidade,
			String nomeDoAtributo, Object valorDoAtributo) {
		List<E> retorno = null;
		try {
			retorno = baseBO.pesquisarComUmFiltro(classeDaEntidade,
					nomeDoAtributo, valorDoAtributo);
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
		}
		return retorno;
	}

	public List<E> buscarTodos(Class<E> classeEntidade) {
		List<E> retorno = null;
		try {
			retorno = baseBO.buscarTodos(classeEntidade);
		} catch (NegocioException e) {
			MBeanUtils.adicionarMensagemErro(e.getMessage());
		}
		return retorno;
	}
}
