package br.com.fsinformatica.projeto.comum.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tb_servico")
public class Servico implements Entidade
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_servico")
	private Long id;

	@Column(name="fk_imovel")
	private Long imovel;
	
	@Column(name="fk_inquilino")
	private Long inquilino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImovel() {
		return imovel;
	}

	public void setImovel(Long imovel) {
		this.imovel = imovel;
	}

	public Long getInquilino() {
		return inquilino;
	}

	public void setInquilino(Long inquilino) {
		this.inquilino = inquilino;
	}

}
