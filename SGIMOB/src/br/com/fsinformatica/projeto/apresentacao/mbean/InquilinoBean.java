package br.com.fsinformatica.projeto.apresentacao.mbean;

import java.util.List;

import br.com.fsinformatica.projeto.apresentacao.EntidadeBean;
import br.com.fsinformatica.projeto.comum.modelo.Inquilino;

public class InquilinoBean extends EntidadeBean<Inquilino> {

	private Inquilino inquilino;
	private String pesquisa;
	
	public InquilinoBean() {
		inquilino = new Inquilino();
	}

	public String incluir() {
		return super.incluir(inquilino);
	}

	
	public String alterar() {
		return super.alterar(inquilino);
	}

	
	public String excluir() {
		return super.excluir(inquilino);
	}

	
	public Inquilino recuperar() {
		return super.recuperar(Inquilino.class,Long.parseLong(pesquisa));
	}

	
	public List<Inquilino> pesquisarPorNome() {
		return super.pesquisarPorNome(Inquilino.class, "nome", pesquisa);
	}

	
	public List<Inquilino> buscarTodos() {
		return super.buscarTodos(Inquilino.class);
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}


}
