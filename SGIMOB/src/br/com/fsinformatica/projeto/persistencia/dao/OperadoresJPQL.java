package br.com.fsinformatica.projeto.persistencia.dao;

public enum OperadoresJPQL {
	IGUAL(" = "), CONTEM(" like "), COMECA_COM(" like "), TERMINA_COM(" like "), MAIOR(
			" > "), MAIOR_IGUAL(" >= "), MENOR(" < "), MENOR_IGUAL(" <= "), DIFERENTE(
			" <> ");

	private String valor;

	private OperadoresJPQL(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
