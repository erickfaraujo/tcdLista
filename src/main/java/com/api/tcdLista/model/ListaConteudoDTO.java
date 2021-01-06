package com.api.tcdLista.model;

import java.util.Collection;

public class ListaConteudoDTO {

	private int tipoLista;
	private Collection<Long> conteudos;
	//private Collection<Long> assistidos;

	public Collection<Long> getConteudos() {
		return conteudos;
	}

	public void setConteudos(Collection<Long> conteudos) {
		this.conteudos = conteudos;
	}

	public int getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}


}
