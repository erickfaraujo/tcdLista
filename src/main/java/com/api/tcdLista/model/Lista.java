package com.api.tcdLista.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Lista {

	@Id
	private Long id;

	@Column
	private int userId;

	@ManyToOne
	@JoinColumn(name = "id_tipo_lista", nullable = false)
	private TipoLista tipoLista;

	@ManyToMany
	@JoinColumn(name = "id_conteudo", nullable = false)
	private Collection<Conteudo> conteudos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public TipoLista getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(TipoLista tipoLista) {
		this.tipoLista = tipoLista;
	}

	public Collection<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(Collection<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

}
