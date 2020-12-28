package com.api.tcdLista.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;


@Entity
public class Conteudo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 50)
	private String titulo;

	@Column(nullable = false, length = 50)
	private String descricao;

	@Column(nullable = false)
	private Date dataLancamento;

	@ManyToOne
	private TipoConteudo tipoConteudo;

	@ManyToOne
	private Classificacao classificacao;

	@Column(nullable = false)
	private int quantidadeVisualizacao;

	public Conteudo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public TipoConteudo getIdTipoConteudo() {
		return tipoConteudo;
	}

	public void setIdTipoConteudo(TipoConteudo idTipoConteudo) {
		this.tipoConteudo = idTipoConteudo;
	}

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public void setQuantidadeVisualizacao(int quantidadeVisualizacao) {
		this.quantidadeVisualizacao = quantidadeVisualizacao;
	}
}