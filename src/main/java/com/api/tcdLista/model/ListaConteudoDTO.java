package com.api.tcdLista.model;

import java.util.Collection;

public class ListaConteudoDTO {

	private Collection<Long> myList;
	private Collection<Long> assistidos;

	public Collection<Long> getMyList() {
		return myList;
	}

	public void setMyList(Collection<Long> myList) {
		this.myList = myList;
	}

	public Collection<Long> getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(Collection<Long> assistidos) {
		this.assistidos = assistidos;
	}

}
