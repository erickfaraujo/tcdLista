package com.api.tcdLista.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoLista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_lista")
	private int id;
	
	@Column
	private String desricao;
	
	
	public TipoLista() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesricao() {
		return desricao;
	}

	public void setDesricao(String desricao) {
		this.desricao = desricao;
	}

}
