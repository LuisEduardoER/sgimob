package br.com.fsinformatica.projeto.apresentacao;

import java.io.Serializable;
import java.util.List;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;
import br.com.fsinformatica.projeto.negocio.bo.BaseBO;

public abstract class EntidadeBean<E extends Entidade> {

	BaseBO<E> baseBO = new BaseBO<E>();

	public String incluir(E entidade) {
		try {
			baseBO.incluir(entidade);
			return "sucesso";
		} catch (NegocioException e) {
			return "erro";
		}
	}

	public String alterar(E entidade) {
		try {
			baseBO.alterar(entidade);
			return "sucesso";
		} catch (NegocioException e) {
			return "erro";
		}
	}

	public String excluir(E entidade) {
		try {
			baseBO.excluir(entidade);
			return "sucesso";
		} catch (NegocioException e) {
			return "erro";
		}
	}

	public E recuperar(Class<E> classeEntidade, Serializable id) {
		try {
			return baseBO.buscar(classeEntidade, id);
		} catch (NegocioException e) {
			return null;
		}
	}

	public List<E> pesquisarPorNome(Class<E> classeDaEntidade,
			String nomeDoAtributo, Object valorDoAtributo) {
		try {
			return baseBO.pesquisarComUmFiltro(classeDaEntidade,
					nomeDoAtributo, valorDoAtributo);
		} catch (NegocioException e) {
			return null;
		}
	}

	public List<E> buscarTodos(Class<E> classeEntidade) {
		try {
			return baseBO.buscarTodos(classeEntidade);
		} catch (NegocioException e) {
			return null;
		}
	}

	public abstract String incluir();

	public abstract String alterar();

	public abstract String excluir();

	public abstract E recuperar();

	public abstract List<E> pesquisarPorNome();

	public abstract List<E> buscarTodos();
}
