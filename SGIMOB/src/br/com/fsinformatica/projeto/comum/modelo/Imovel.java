package br.com.fsinformatica.projeto.comum.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_imovel")
public class Imovel implements Entidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_imovel")
	private Long id;

	@Column(name = "fk_tipo_imovel")
	private Long tipo_imovel;

	@Column(name = "fk_proprietario_imovel")
	private Long proprietario;

	@Column(name = "tamanho_imovel")
	private Double tamanho;

	@Column(name = "endereco_imovel")
	private String endereco;

	@Column(name = "descricao_imovel")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTipo_imovel() {
		return tipo_imovel;
	}

	public void setTipo_imovel(Long tipo_imovel) {
		this.tipo_imovel = tipo_imovel;
	}

	public Long getProprietario() {
		return proprietario;
	}

	public void setProprietario(Long proprietario) {
		this.proprietario = proprietario;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
