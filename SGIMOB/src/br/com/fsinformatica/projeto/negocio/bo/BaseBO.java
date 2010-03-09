package br.com.fsinformatica.projeto.negocio.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.excecao.PersistenciaException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;
import br.com.fsinformatica.projeto.comum.utils.validacao.OperacaoJPA;
import br.com.fsinformatica.projeto.comum.utils.validacao.Validador;
import br.com.fsinformatica.projeto.persistencia.dao.BaseDAO;
import br.com.fsinformatica.projeto.persistencia.dao.OperadoresJPQL;

public class BaseBO<E extends Entidade> {

	public BaseDAO<E> dao = new BaseDAO<E>();

	public E incluir(E entidade) throws NegocioException {
		try {
			return dao.incluir(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public E incluirComValidacao(E entidade) throws NegocioException {
		try {
			Validador.validarUsandoAnotacoes(entidade, OperacaoJPA.INCLUSAO);
			return dao.incluir(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public E alterar(E entidade) throws NegocioException {
		try {
			return dao.alterar(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public E alterarComValidacao(E entidade) throws NegocioException {
		try {
			Validador.validarUsandoAnotacoes(entidade, OperacaoJPA.ALTERACAO);
			return dao.alterar(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public void excluir(E entidade) throws NegocioException {
		try {
			dao.excluir(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public void excluirComValidacao(E entidade) throws NegocioException {
		try {
			Validador.validarUsandoAnotacoes(entidade, OperacaoJPA.EXCLUSAO);
			dao.excluir(entidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public E buscar(Class<E> classeDaEntidade, Serializable id)
			throws NegocioException {
		try {
			return dao.buscar(classeDaEntidade, id);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> buscarTodos(Class<E> classeDaEntidade)
			throws NegocioException {
		try {
			return dao.buscarTodos(classeDaEntidade);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> buscarComUmFiltro(Class<E> classeDaEntidade,
			String nomeDoAtributo, Object valorDoAtributo)
			throws NegocioException {
		try {
			return dao.buscarComUmFiltro(classeDaEntidade, nomeDoAtributo,
					valorDoAtributo);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> buscarComUmFiltro(Class<E> classeDaEntidade,
			String nomeDoAtributo, OperadoresJPQL operador,
			Object valorDoAtributo) throws NegocioException {
		try {
			return dao.buscarComUmFiltro(classeDaEntidade, nomeDoAtributo,
					operador, valorDoAtributo);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> pesquisarComUmFiltro(final Class<E> classeDaEntidade,
			final String nomeDoAtributo, final Object valorDoAtributo)
			throws NegocioException{
		try {
			return dao.pesquisarComUmFiltro(classeDaEntidade, nomeDoAtributo, valorDoAtributo);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> consultar(String nomeDaNamedQuery,
			Map<String, ? extends Object> parametros,
			Integer quantidadeResultados) throws NegocioException {
		try {
			return dao.consultar(nomeDaNamedQuery, parametros,
					quantidadeResultados);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> consultar(String nomeDaNamedQuery,
			Map<String, ? extends Object> parametros) throws NegocioException {
		try {
			return dao.consultar(nomeDaNamedQuery, parametros);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}

	public List<E> consultarSqlNativo(String sqlNativo,
			Map<String, ? extends Object> parametros,
			Integer quantidadeResultados) throws NegocioException {
		try {
			return dao.consultarSqlNativo(sqlNativo, parametros,
					quantidadeResultados);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
	}
}
