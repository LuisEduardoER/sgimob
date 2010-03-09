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

	@Override
	public String incluir() {
		return super.incluir(inquilino);
	}

	@Override
	public String alterar() {
		return super.alterar(inquilino);
	}

	@Override
	public String excluir() {
		return super.excluir(inquilino);
	}

	@Override
	public Inquilino recuperar() {
		return super.recuperar(Inquilino.class,Long.parseLong(pesquisa));
	}

	@Override
	public List<Inquilino> pesquisarPorNome() {
		return super.pesquisarPorNome(Inquilino.class, "nome", pesquisa);
	}

	@Override
	public List<Inquilino> buscarTodos() {
		return super.buscarTodos(Inquilino.class);
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public String getPesquisa() {
		return pesquisa;
	}

}
