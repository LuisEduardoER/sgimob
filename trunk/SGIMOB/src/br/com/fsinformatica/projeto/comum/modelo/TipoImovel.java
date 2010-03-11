package br.com.fsinformatica.projeto.comum.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tb_tipo_imovel")
public class TipoImovel implements Entidade{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_tipo_imovel")
	private Long id;
	
	@Column(name="nome_tipo_imovel")
	private String nome;

	@Column(name="descricao_tipo_imovel")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
