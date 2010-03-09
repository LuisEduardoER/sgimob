package br.com.fsinformatica.projeto.apresentacao.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.fsinformatica.projeto.apresentacao.EntidadeBean;
import br.com.fsinformatica.projeto.comum.modelo.Inquilino;

public class InquilinoBean extends EntidadeBean<Inquilino> {

	private Inquilino inquilino;
	private String pesquisa;
	private List<SelectItem> listaItems;
	public InquilinoBean() {
		inquilino = new Inquilino();
		listaItems = new ArrayList<SelectItem>();
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

	public List<SelectItem> listaItens() {
		listaItems.add(new SelectItem(null, "- Selecione -"));
		for (Inquilino inquilino : this.buscarTodos()) {
			listaItems.add(new SelectItem(inquilino.getId(), inquilino.getNome()));
		}
		return listaItems;

	}
	
	public List<Inquilino> buscarTodos() {
		return super.buscarTodos(Inquilino.class);
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public List<SelectItem> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<SelectItem> listaItems) {
		this.listaItems = listaItems;
	}

}
