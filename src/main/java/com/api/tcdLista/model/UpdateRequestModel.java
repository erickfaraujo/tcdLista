package com.api.tcdLista.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequestModel {

	private int userId;
	private int tipoLista;
	private long idConteudo;

	public UpdateRequestModel() {
		super();
	}

	public UpdateRequestModel(@JsonProperty("userId") int userId, @JsonProperty("tipoLista") int tipoLista,
			@JsonProperty("idConteudo") int idConteudo) {
		super();
		
		this.userId = userId;
		this.tipoLista = tipoLista;
		this.idConteudo = idConteudo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}

	public long getIdConteudo() {
		return idConteudo;
	}

	public void setIdConteudo(long idConteudo) {
		this.idConteudo = idConteudo;
	}
}
