package br.com.fsinformatica.projeto.comum.excecao;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException() {
		super();
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(Throwable cause) {
		super(cause);
	}
}
