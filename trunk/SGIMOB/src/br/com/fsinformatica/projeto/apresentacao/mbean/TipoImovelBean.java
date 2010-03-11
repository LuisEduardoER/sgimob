package br.com.fsinformatica.projeto.apresentacao.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.fsinformatica.projeto.apresentacao.EntidadeBean;
import br.com.fsinformatica.projeto.comum.modelo.TipoImovel;

public class TipoImovelBean extends EntidadeBean<TipoImovel> {

	private TipoImovel tipoImovel;
	private String pesquisa;
	private List<SelectItem> listaDeTiposItens;
	
	public TipoImovelBean() {
		tipoImovel = new TipoImovel();
		listaDeTiposItens = new ArrayList<SelectItem>();
	}

	
	public String incluir() {
		return super.incluir(tipoImovel);
	}

	
	public String alterar() {
		return super.alterar(tipoImovel);
	}

	
	public String excluir() {
		return super.excluir(tipoImovel);
	}

	
	public TipoImovel recuperar() {
		return super.recuperar(TipoImovel.class,tipoImovel.getId());
	}

	
	public List<TipoImovel> pesquisarPorNome() {
		return super.pesquisarPorNome(TipoImovel.class, "nome", pesquisa);
	}

	
	public List<TipoImovel> buscarTodos() {
		return super.buscarTodos(TipoImovel.class);
	}

	public List<SelectItem> listarItens() {
		listaDeTiposItens.add(new SelectItem(null,"-- Selecione --"));
		for (TipoImovel tipoImovel : this.buscarTodos()) {
			listaDeTiposItens.add(new SelectItem(tipoImovel.getId(),tipoImovel.getNome()));
		}
		return listaDeTiposItens;
	}
	
	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public List<SelectItem> getListaDeTiposItens() {
		listaDeTiposItens = this.listarItens();
		return listaDeTiposItens;
	}

}
