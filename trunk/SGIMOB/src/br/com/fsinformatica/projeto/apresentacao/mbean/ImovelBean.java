package br.com.fsinformatica.projeto.apresentacao.mbean;

import java.util.List;

import br.com.fsinformatica.projeto.apresentacao.EntidadeBean;
import br.com.fsinformatica.projeto.comum.modelo.Imovel;

public class ImovelBean extends EntidadeBean<Imovel> {

	private Imovel imovel;
	private String pesquisa;
	
	public ImovelBean() {
		imovel = new Imovel();
	}

	public String incluir() {
		return super.incluir(imovel);
	}

	public String alterar() {
		return super.alterar(imovel);
	}

	
	public String excluir() {
		return super.excluir(imovel);
	}

	
	public Imovel recuperar() {
		return super.recuperar(Imovel.class,imovel.getId());
	}

	
	public List<Imovel> pesquisarPorNome() {
		return super.pesquisarPorNome(Imovel.class, "nome", pesquisa);
	}

	
	public List<Imovel> buscarTodos() {
		return super.buscarTodos(Imovel.class);
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

}
