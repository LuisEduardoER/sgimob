package br.com.fsinformatica.projeto.comum.utils.validacao;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.fsinformatica.projeto.comum.anotacao.Label;
import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;
import br.com.fsinformatica.projeto.comum.utils.PropertiesUtils;

class ValidadorJPA {

	static void validarUsandoAnotacoes(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {

		if (!ehEntidadeJPA(entidade)) {
			throw new NegocioException("Entidade inválida!");
		}

		validarId(entidade, operacao);
		validarColumn(entidade, operacao);
		validarColumnNaoAnotada(entidade, operacao);
		validarOneToOne(entidade, operacao);
		validarOneToMany(entidade, operacao);
		validarManyToOne(entidade, operacao);
		validarManyToMany(entidade, operacao);
	}

	private static void validarId(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade, Id.class)) {
			boolean temGeracaoAutomatica = membrosAnotadosCom(entidade,
					GeneratedValue.class).contains(membro);

			Object valorDoId = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			if (OperacaoJPA.INCLUSAO == operacao && temGeracaoAutomatica
					&& valorDoId != null) {

				String msg = "O atributo " + obterDescricaoDoMembro(membro)
						+ " da entidade " + obterDescricaoDaEntidade(entidade)
						+ " não pode ter valor, visto que é gerado "
						+ "automaticamente pela aplicação.";
				lancarExcecao(entidade, membro,
						"@GeneratedValue.comIdNaoNulo.incluir", msg);
			} else if (OperacaoJPA.INCLUSAO == operacao
					&& !temGeracaoAutomatica && valorDoId == null) {
				String msg = "Não é possível incluir a entidade "
						+ obterDescricaoDaEntidade(entidade)
						+ "sem o preenchimento do atributo "
						+ obterDescricaoDoMembro(membro) + ".";
				lancarExcecao(entidade, membro, "@Id.comIdNulo.incluir", msg);
			} else if (OperacaoJPA.ALTERACAO == operacao && valorDoId == null) {
				String msg = "Não é possível alterar a entidade "
						+ obterDescricaoDaEntidade(entidade)
						+ "sem o preenchimento do atributo "
						+ obterDescricaoDoMembro(membro) + ".";
				lancarExcecao(entidade, membro, "@Id.comIdNulo.alterar", msg);
			} else if (OperacaoJPA.EXCLUSAO == operacao && valorDoId == null) {
				String msg = "Não é possível excluir a entidade "
						+ obterDescricaoDaEntidade(entidade)
						+ "sem o preenchimento do atributo "
						+ obterDescricaoDoMembro(membro) + ".";
				lancarExcecao(entidade, membro, "@Id.comIdNulo.excluir", msg);
			}
		}
	}

	private static void validarColumn(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade,
				Column.class)) {
			boolean temGeracaoAutomatica = membrosAnotadosCom(entidade,
					GeneratedValue.class).contains(membro);

			Column column = membro.getAnnotation(Column.class);

			Object valorDoMembro = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			if (OperacaoJPA.INCLUSAO == operacao && !column.insertable()) {
				continue;
			}

			if (OperacaoJPA.ALTERACAO == operacao && !column.updatable()) {
				continue;
			}

			if (OperacaoJPA.EXCLUSAO == operacao) {
				break;
			}

			if (!temGeracaoAutomatica
					&& !column.nullable()
					&& (valorDoMembro == null || valorDoMembro.toString()
							.trim().length() == 0)) {

				String msg = "Atributo " + obterDescricaoDoMembro(membro)
						+ " é de preenchimento obrigatório.";
				lancarExcecao(entidade, membro, "@Column.nullableFalse", msg);
			}

			if (valorDoMembro instanceof String
					&& column.length() < valorDoMembro.toString().length()) {
				String msg = "Atributo " + obterDescricaoDoMembro(membro)
						+ " ultrapassou o tamanho máximo de " + column.length()
						+ ".";
				lancarExcecao(entidade, membro, "@Column.acimaTamanho", msg);
			}
		}
	}

	private static void validarColumnNaoAnotada(Entidade entidade,
			OperacaoJPA operacao) throws NegocioException {

		for (AccessibleObject membro : membrosNaoAnotadosCom(entidade,
				Column.class)) {

			Object valorDoMembro = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			if (OperacaoJPA.EXCLUSAO == operacao) {
				break;
			}

			if (valorDoMembro instanceof String
					&& 255 < valorDoMembro.toString().length()) {

				String msg = "Atributo " + obterDescricaoDoMembro(membro)
						+ " ultrapassou o tamanho máximo de " + 255 + ".";
				lancarExcecao(entidade, membro, "@Column.acimaTamanho", msg);
			}
		}
	}

	private static void validarOneToOne(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade,
				OneToOne.class)) {
			OneToOne oneToOne = membro.getAnnotation(OneToOne.class);
			Object valorDoMembro = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			if (!oneToOne.optional()
					&& (valorDoMembro == null || valorDoMembro.toString()
							.trim().length() == 0)) {
				String msg = "O atributo " + obterDescricaoDoMembro(membro)
						+ " é de preenchimento obrigatório.";
				lancarExcecao(entidade, membro, "@OneToOne.optionalFalse", msg);
			}

			boolean temCascadePersist = false;
			if (oneToOne.cascade() != null && oneToOne.cascade().length > 0) {

				for (CascadeType cascadeType : oneToOne.cascade()) {
					if (cascadeType == CascadeType.PERSIST
							|| cascadeType == CascadeType.ALL) {
						temCascadePersist = true;
						break;
					}
				}
			}

			if (!temCascadePersist && operacao == OperacaoJPA.INCLUSAO
					&& valorDoMembro instanceof Entidade) {
				Entidade entidadeRelacionada = (Entidade) valorDoMembro;
				for (AccessibleObject atributoId : membrosAnotadosCom(
						entidadeRelacionada, Id.class)) {
					Object idEntidadeRelacionada = obterValorDoMembro(
							entidadeRelacionada, obterNomeDoMembro(atributoId));
					if (idEntidadeRelacionada == null
							|| idEntidadeRelacionada.toString().trim().length() == 0) {
						String msg = "A entidade "
								+ obterDescricaoDoMembro(membro)
								+ " não existe no banco de dados.";
						lancarExcecao(entidade, membro,
								"@OneToOne.semCascade.comIdNulo", msg);
					}
				}
			}
		}
	}

	private static void validarManyToOne(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade,
				ManyToOne.class)) {
			ManyToOne manyToOne = membro.getAnnotation(ManyToOne.class);
			Object valorDoAtributo = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			if (!manyToOne.optional()
					&& (valorDoAtributo == null || valorDoAtributo.toString()
							.trim().length() == 0)) {
				String msg = "O atributo " + obterDescricaoDoMembro(membro)
						+ " é de preenchimento obrigatório.";
				lancarExcecao(entidade, membro, "@ManyToOne.optionalFalse", msg);
			}

			boolean temCascadePersist = false;
			if (manyToOne.cascade() != null && manyToOne.cascade().length > 0) {

				for (CascadeType cascadeType : manyToOne.cascade()) {
					if (cascadeType == CascadeType.PERSIST
							|| cascadeType == CascadeType.ALL) {
						temCascadePersist = true;
						break;
					}
				}
			}

			if (!temCascadePersist && operacao == OperacaoJPA.INCLUSAO
					&& valorDoAtributo instanceof Entidade) {
				Entidade entidadeRelacionada = (Entidade) valorDoAtributo;
				for (AccessibleObject membroComId : membrosAnotadosCom(
						entidadeRelacionada, Id.class)) {
					Object idEntidadeRelacionada = obterValorDoMembro(
							entidadeRelacionada, obterNomeDoMembro(membroComId));
					if (idEntidadeRelacionada == null
							|| idEntidadeRelacionada.toString().trim().length() == 0) {
						String msg = "A entidade "
								+ obterDescricaoDoMembro(membro)
								+ " não existe no banco de dados.";
						lancarExcecao(entidade, membro,
								"@ManyToOne.semCascade.comIdNulo", msg);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void validarOneToMany(Entidade entidade, OperacaoJPA operacao)
			throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade,
				OneToMany.class)) {
			OneToMany oneToMany = membro.getAnnotation(OneToMany.class);
			Object valorDoAtributo = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			boolean temCascadePersist = false;
			if (oneToMany.cascade() != null && oneToMany.cascade().length > 0) {

				for (CascadeType cascadeType : oneToMany.cascade()) {
					if (cascadeType == CascadeType.PERSIST
							|| cascadeType == CascadeType.ALL) {
						temCascadePersist = true;
						break;
					}
				}
			}

			if (!temCascadePersist && operacao == OperacaoJPA.INCLUSAO
					&& valorDoAtributo instanceof Collection) {
				Collection entidadesRelacionada = (Collection) valorDoAtributo;
				for (Object objeto : entidadesRelacionada) {
					if (objeto instanceof Entidade) {
						Entidade entidadeRelacionada = (Entidade) objeto;
						for (AccessibleObject membroComId : membrosAnotadosCom(
								entidadeRelacionada, Id.class)) {
							Object idEntidadeRelacionada = obterValorDoMembro(
									entidadeRelacionada,
									obterNomeDoMembro(membroComId));
							if (idEntidadeRelacionada == null
									|| idEntidadeRelacionada.toString().trim()
											.length() == 0) {
								String msg = "É necessário primeiramente salvar o atributo "
										+ obterDescricaoDoMembro(membro) + ".";
								lancarExcecao(entidade, membro,
										"@OneToMany.optionalFalse", msg);
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void validarManyToMany(Entidade entidade,
			OperacaoJPA operacao) throws NegocioException {
		for (AccessibleObject membro : membrosAnotadosCom(entidade,
				ManyToMany.class)) {
			ManyToMany manyToMany = membro.getAnnotation(ManyToMany.class);
			Object valorDoAtributo = obterValorDoMembro(entidade,
					obterNomeDoMembro(membro));

			boolean temCascadePersist = false;
			if (manyToMany.cascade() != null && manyToMany.cascade().length > 0) {

				for (CascadeType cascadeType : manyToMany.cascade()) {
					if (cascadeType == CascadeType.PERSIST
							|| cascadeType == CascadeType.ALL) {
						temCascadePersist = true;
						break;
					}
				}
			}

			if (!temCascadePersist && operacao == OperacaoJPA.INCLUSAO
					&& valorDoAtributo instanceof Collection) {
				Collection entidadesRelacionada = (Collection) valorDoAtributo;
				for (Object objeto : entidadesRelacionada) {
					if (objeto instanceof Entidade) {
						Entidade entidadeRelacionada = (Entidade) objeto;
						for (AccessibleObject atributoId : membrosAnotadosCom(
								entidadeRelacionada, Id.class)) {
							Object idEntidadeRelacionada = obterValorDoMembro(
									entidadeRelacionada,
									obterNomeDoMembro(atributoId));
							if (idEntidadeRelacionada == null
									|| idEntidadeRelacionada.toString().trim()
											.length() == 0) {
								String msg = "É necessário primeiramente salvar o atributo "
										+ obterDescricaoDoMembro(membro) + ".";
								lancarExcecao(entidade, membro,
										"@ManyToMany.optionalFalse", msg);
							}
						}
					}
				}
			}
		}
	}

	private static List<AccessibleObject> membrosAnotadosCom(Entidade entidade,
			Class<? extends Annotation> classeDaAnotacao) {

		List<AccessibleObject> membros = new ArrayList<AccessibleObject>();
		StringBuilder nome = new StringBuilder(classeDaAnotacao.getName());

		if (atributosAnotados(entidade)) {
			for (Field atributo : entidade.getClass().getDeclaredFields()) {
				for (Annotation anotacao : atributo.getAnnotations()) {
					if (anotacao != null && anotacao.toString().contains(nome)) {
						membros.add(atributo);
					}
				}
			}
		} else if (metodosAnotados(entidade)) {
			for (Method metodo : entidade.getClass().getDeclaredMethods()) {
				for (Annotation anotacao : metodo.getAnnotations()) {
					if (anotacao != null && anotacao.toString().contains(nome)) {
						membros.add(metodo);
					}
				}
			}
		}

		return membros;
	}

	private static List<AccessibleObject> membrosNaoAnotadosCom(
			Entidade entidade, Class<? extends Annotation> classeDaAnotacao) {

		List<AccessibleObject> membros = new ArrayList<AccessibleObject>();
		StringBuilder nome = new StringBuilder(classeDaAnotacao.getName());

		if (atributosAnotados(entidade)) {
			for (Field atributo : entidade.getClass().getDeclaredFields()) {
				if (atributo.getAnnotations() == null
						|| atributo.getAnnotations().length == 0) {
					membros.add(atributo);
				}
				boolean encontrouAnotacao = false;
				for (Annotation anotacao : atributo.getAnnotations()) {
					if (anotacao.toString().contains(nome)) {
						encontrouAnotacao = true;
					}
				}

				if (!encontrouAnotacao) {
					membros.add(atributo);
				}
			}
		} else if (metodosAnotados(entidade)) {
			for (Method metodo : entidade.getClass().getDeclaredMethods()) {
				if (metodo.getAnnotations() == null
						|| metodo.getAnnotations().length == 0) {
					membros.add(metodo);
				}
				boolean encontrouAnotacao = false;
				for (Annotation anotacao : metodo.getAnnotations()) {
					if (anotacao.toString().contains(nome)) {
						encontrouAnotacao = true;
					}
				}

				if (!encontrouAnotacao) {
					membros.add(metodo);
				}
			}
		}

		return membros;
	}

	private static boolean ehEntidadeJPA(Entidade entidade) {
		if (entidade == null) {
			return false;
		}

		for (Annotation anotacao : entidade.getClass().getAnnotations()) {
			if (anotacao instanceof Entity) {
				return true;
			}
		}
		return false;
	}

	private static Object obterValorDoMembro(Entidade entidade, String nome) {

		for (Field atributo : entidade.getClass().getDeclaredFields()) {
			if (atributo.getName().equals(nome)) {
				try {
					atributo.setAccessible(true);
					Object valor = atributo.get(entidade);
					atributo.setAccessible(false);
					return valor;
				} catch (Exception e) {
				}
			}
		}

		for (Method metodo : entidade.getClass().getDeclaredMethods()) {
			if (metodo.getName().equals(nome)) {
				try {
					return metodo.invoke(entidade);
				} catch (Exception e) {
				}
			}
		}

		return null;
	}

	private static String obterNomeDoMembro(AccessibleObject membro) {

		String retorno = null;
		Field atributo = null;
		if (membro instanceof Field) {
			atributo = (Field) membro;
			retorno = atributo.getName();
		}

		Method metodo = null;
		if (membro instanceof Method) {
			metodo = (Method) membro;
			retorno = metodo.getName();
		}

		return retorno;
	}

	private static String obterDescricaoDoMembro(AccessibleObject membro) {

		String retorno = null;
		Field atributo = null;
		if (membro instanceof Field) {
			atributo = (Field) membro;
			Label label = atributo.getAnnotation(Label.class);
			retorno = (label != null && label.value() != null) ? label.value()
					: atributo.getName();
		}

		Method metodo = null;
		if (membro instanceof Method) {
			metodo = (Method) membro;
			Label label = metodo.getAnnotation(Label.class);
			retorno = (label != null && label.value() != null) ? label.value()
					: metodo.getName();
		}

		return retorno;
	}

	private static String obterNomeDaEntidade(Entidade entidade) {
		return entidade.getClass().getSimpleName();
	}

	private static String obterDescricaoDaEntidade(Entidade entidade) {
		Label label = entidade.getClass().getAnnotation(Label.class);
		return (label != null && label.value() != null) ? label.value()
				: entidade.getClass().getSimpleName();
	}

	private static boolean atributosAnotados(Entidade entidade) {
		for (Field atributo : entidade.getClass().getDeclaredFields()) {
			for (Annotation anotacao : atributo.getAnnotations()) {
				if (anotacao != null
						&& anotacao.toString().contains(Id.class.getName())) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean metodosAnotados(Entidade entidade) {
		for (Method atributo : entidade.getClass().getDeclaredMethods()) {
			for (Annotation anotacao : atributo.getAnnotations()) {
				if (anotacao != null
						&& anotacao.toString().contains(Id.class.getName())) {
					return true;
				}
			}
		}

		return false;
	}

	private static void lancarExcecao(Entidade entidade,
			AccessibleObject membro, String tipoErro, String mensagem)
			throws NegocioException {

		String chave = obterNomeDaEntidade(entidade) + "."
				+ obterNomeDoMembro(membro);
		chave += "." + tipoErro;
		String valor = PropertiesUtils.getProperty(chave);

		if (valor != null && valor.trim().length() > 0) {
			throw new NegocioException(valor);
		}

		throw new NegocioException(mensagem);
	}
}
