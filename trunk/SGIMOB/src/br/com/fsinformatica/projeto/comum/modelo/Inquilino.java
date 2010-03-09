package br.com.fsinformatica.projeto.comum.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tb_inquilino")
public class Inquilino implements Entidade
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_inquilino")
	private Long id;
	
	@Column(name="nome_inquilino")
	private String nome;
	
	@Column(name="data_nascimento_inquilino")
	private Date data_nascimento;
	
	@Column(name="endereco_imovel")
	private String endereco;
	
	@Column(name="telefone_imovel")
	private String telefone;

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

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	} 
	
}
