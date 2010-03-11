package br.com.fsinformatica.projeto.comum.utils.validacao;

import br.com.fsinformatica.projeto.comum.excecao.NegocioException;
import br.com.fsinformatica.projeto.comum.modelo.Entidade;

public class Validador {

	private Validador() {
	}

	public static void validarUsandoAnotacoes(Entidade entidade,
			OperacaoJPA operacao) throws NegocioException {
		ValidadorJPA.validarUsandoAnotacoes(entidade, operacao);
	}

	public static void naoNulo(Object objeto, String msgErro)
			throws NegocioException {
		if (objeto == null) {
			throw new NegocioException(msgErro);
		}
	}

	public static void naoVazio(String sentenca, String msgErro)
			throws NegocioException {
		if (sentenca == null || sentenca.trim().length() == 0) {
			throw new NegocioException(msgErro);
		}
	}

	public static void ehMaiorQueZero(Number valor, String msgErro)
			throws NegocioException {
		if (valor == null || valor.doubleValue() <= 0) {
			throw new NegocioException(msgErro);
		}
	}
}
