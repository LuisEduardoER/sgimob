package br.com.fsinformatica.projeto.apresentacao.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.fsinformatica.projeto.apresentacao.EntidadeBean;
import br.com.fsinformatica.projeto.comum.modelo.Proprietario;


public class ProprietarioBean extends EntidadeBean<Proprietario> {

	private Proprietario proprietario;
	private String pesquisa;
	private List<SelectItem> listaDeProprietarioItens;
	
	public ProprietarioBean() {
		proprietario = new Proprietario();
		listaDeProprietarioItens = new ArrayList<SelectItem>();
	}

	public String incluir() {
		return super.incluir(proprietario);
	}

	public String alterar() {
		return super.alterar(proprietario);
	}

	public String excluir() {
		return super.excluir(proprietario);
	}

	public Proprietario recuperar() {
		return super.recuperar(Proprietario.class,proprietario.getId());
	}

	public List<Proprietario> pesquisarPorNome() {
		return super.pesquisarPorNome(Proprietario.class, "nome", pesquisa);
	}

	public List<Proprietario> buscarTodos() {
		return super.buscarTodos(Proprietario.class);
	}

	public List<SelectItem> listarItens() {
		listaDeProprietarioItens.add(new SelectItem(null,"-- Selecione --"));
		for (Proprietario proprietario : this.buscarTodos()) {
			listaDeProprietarioItens.add(new SelectItem(proprietario.getId(),proprietario.getNome()));
		}
		return listaDeProprietarioItens;
	}
	
	public Proprietario getProprietario() {
		return proprietario;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public List<SelectItem> getListaDeProprietarioItens() {
		listaDeProprietarioItens = this.listarItens();
		return listaDeProprietarioItens;
	}

}
