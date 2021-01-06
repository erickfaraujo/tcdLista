package com.api.tcdLista.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ListaConteudo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lista_conteudo", nullable = false, updatable = false)
	private long idListaConteudo;

	@ManyToOne
	@JoinColumn(name = "id_lista", nullable = false)
	private Lista lista;

	@Column(name = "id_conteudo", nullable = false)
	private Long idConteudo;

	public ListaConteudo() {
		super();
	}

	public long getIdListaConteudo() {
		return idListaConteudo;
	}

	public void setIdListaConteudo(long idListaConteudo) {
		this.idListaConteudo = idListaConteudo;
	}

	public Lista getLista() {
		return lista;
	}

	public void setIdLista(Lista lista) {
		this.lista = lista;
	}

	public Long getIdConteudo() {
		return idConteudo;
	}

	public void setIdConteudo(Long idConteudo) {
		this.idConteudo = idConteudo;
	}

}
