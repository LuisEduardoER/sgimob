package br.com.fsinformatica.projeto.persistencia.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fsinformatica.projeto.comum.excecao.PersistenciaException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;

public class BaseDAO<E extends Entidade> {

	protected EntityManagerFactory emf;

	public BaseDAO() {
		this("conexao");
	}

	public BaseDAO(String nomeUnidadePersistencia) {
		emf = Persistence.createEntityManagerFactory(nomeUnidadePersistencia);
	}

	public E incluir(E entidade) throws PersistenciaException {
		EntityTransaction transaction = null;
		try {
			EntityManager em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(entidade);
			transaction.commit();
			return entidade;
		} catch (RuntimeException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			System.out.println(e.getMessage());
			throw new PersistenciaException(e.getMessage());
		}
	}

	public E alterar(E entidade) throws PersistenciaException {
		EntityTransaction transaction = null;
		try {
			EntityManager em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(em.merge(entidade));
			transaction.commit();
			return entidade;
		} catch (RuntimeException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaException(e);
		}
	}

	public void excluir(E entidade) throws PersistenciaException {
		EntityTransaction transaction = null;
		try {
			EntityManager em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			em.remove(em.merge(entidade));
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaException(e);
		}
	}

	public E buscar(Class<E> classeDaEntidade, Serializable id)
			throws PersistenciaException {

		try {
			EntityManager em = emf.createEntityManager();
			return em.find(classeDaEntidade, id);
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> buscarTodos(Class<E> classeDaEntidade)
			throws PersistenciaException {

		try {
			EntityManager em = emf.createEntityManager();

			String consulta = "select e from "
					+ classeDaEntidade.getSimpleName() + " e";

			return em.createQuery(consulta).getResultList();
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	public List<E> buscarComUmFiltro(Class<E> classeDaEntidade,
			String nomeDoAtributo, Object valorDoAtributo)
			throws PersistenciaException {

		try {
			return buscarComUmFiltro(classeDaEntidade, nomeDoAtributo,
					OperadoresJPQL.IGUAL, valorDoAtributo);
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	public List<E> pesquisarComUmFiltro(Class<E> classeDaEntidade,
			String nomeDoAtributo, Object valorDoAtributo)
			throws PersistenciaException {
		try {
			return buscarComUmFiltro(classeDaEntidade, nomeDoAtributo,
					OperadoresJPQL.CONTEM, valorDoAtributo);
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> buscarComUmFiltro(Class<E> classeDaEntidade,
			String nomeDoAtributo, OperadoresJPQL operador,
			Object valorDoAtributo) throws PersistenciaException {

		try {

			EntityManager em = emf.createEntityManager();

			StringBuilder consulta = new StringBuilder(40);
			consulta.append("select e from ");
			consulta.append(classeDaEntidade.getSimpleName());
			consulta.append(" e where e.");
			consulta.append(nomeDoAtributo);

			if (OperadoresJPQL.IGUAL.equals(operador)) {
				consulta.append(OperadoresJPQL.IGUAL.getValor());
			} else if (OperadoresJPQL.CONTEM.equals(operador)) {
				consulta.append(OperadoresJPQL.CONTEM.getValor());
			} else if (OperadoresJPQL.COMECA_COM.equals(operador)) {
				consulta.append(OperadoresJPQL.COMECA_COM.getValor());
			} else if (OperadoresJPQL.TERMINA_COM.equals(operador)) {
				consulta.append(OperadoresJPQL.TERMINA_COM.getValor());
			} else if (OperadoresJPQL.MAIOR.equals(operador)) {
				consulta.append(OperadoresJPQL.MAIOR.getValor());
			} else if (OperadoresJPQL.MAIOR_IGUAL.equals(operador)) {
				consulta.append(OperadoresJPQL.MAIOR_IGUAL.getValor());
			} else if (OperadoresJPQL.MENOR.equals(operador)) {
				consulta.append(OperadoresJPQL.MENOR.getValor());
			} else if (OperadoresJPQL.MENOR_IGUAL.equals(operador)) {
				consulta.append(OperadoresJPQL.MENOR_IGUAL.getValor());
			} else if (OperadoresJPQL.DIFERENTE.equals(operador)) {
				consulta.append(OperadoresJPQL.DIFERENTE.getValor());
			} else {
				consulta.append(" = ");
			}

			consulta.append(":" + nomeDoAtributo.toLowerCase());

			Query query = em.createQuery(consulta.toString());

			if (OperadoresJPQL.CONTEM.equals(operador)) {
				query.setParameter(nomeDoAtributo.toLowerCase(), "%"
						+ valorDoAtributo.toString() + "%");
			} else if (OperadoresJPQL.COMECA_COM.equals(operador)) {
				query.setParameter(nomeDoAtributo.toLowerCase(), " %"
						+ valorDoAtributo);
			} else if (OperadoresJPQL.TERMINA_COM.equals(operador)) {
				query.setParameter(nomeDoAtributo.toLowerCase(),
						valorDoAtributo + "%");
			} else {
				query.setParameter(nomeDoAtributo.toLowerCase(),
						valorDoAtributo);
			}

			return query.getResultList();
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> consultar(String nomeDaNamedQuery,
			Map<String, ? extends Object> parametros,
			Integer quantidadeResultados) throws PersistenciaException {

		try {
			EntityManager em = emf.createEntityManager();
			List<E> resultado = null;

			Query query = em.createNamedQuery(nomeDaNamedQuery);
			if (quantidadeResultados != null) {
				query.setMaxResults(quantidadeResultados);
			}

			if ((parametros != null) && (parametros.size() > 0)) {
				for (String paramName : parametros.keySet()) {
					query.setParameter(paramName, parametros.get(paramName));
				}
			}

			resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	public List<E> consultar(String nomeDaNamedQuery,
			Map<String, ? extends Object> parametros)
			throws PersistenciaException {
		try {
			return this.consultar(nomeDaNamedQuery, parametros, null);
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> consultarSqlNativo(String sqlNativo,
			Map<String, ? extends Object> parametros,
			Integer quantidadeResultados) throws PersistenciaException {

		try {
			EntityManager em = emf.createEntityManager();
			List<E> resultado = null;

			Query query = em.createNativeQuery(sqlNativo);
			if (quantidadeResultados != null) {
				query.setMaxResults(quantidadeResultados);
			}

			if ((parametros != null) && (parametros.size() > 0)) {
				for (String paramName : parametros.keySet()) {
					query.setParameter(paramName, parametros.get(paramName));
				}
			}

			resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			throw new PersistenciaException(e);
		}
	}
}
