package br.com.fsinformatica.projeto.comum.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne(optional=false,targetEntity=Imovel.class)
	@JoinColumn(name="fk_imovel_servico")
	private Imovel imovel;
	
	@ManyToOne(optional=false,targetEntity=Inquilino.class)
	@JoinColumn(name="fk_inquilino_servico")
	private Inquilino inquilino;
	
	public Servico() {
		imovel = new Imovel();
		inquilino = new Inquilino();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	
}
