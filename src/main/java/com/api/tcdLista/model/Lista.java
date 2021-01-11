package com.api.tcdLista.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lista", nullable = false, updatable = false)
	private Long id;

	@Column
	private int userId;

	@ManyToOne
	@JoinColumn(name = "id_tipo_lista", nullable = false)
	private TipoLista tipoLista;
	
	public Lista() {
		super();
	}
	
	public Lista(int userId, TipoLista tipoLista) {
		super();
		
		this.userId = userId;
		this.tipoLista = tipoLista;	
	}

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

}
