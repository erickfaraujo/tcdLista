package com.api.tcdLista.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tcdLista.model.Lista;
import com.api.tcdLista.repository.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository listaRepository;

	public Collection<Lista> getListas(int userId) {
		Collection<Lista> lista = listaRepository.findByUserId(userId);

		if (lista.isEmpty()) {
			//TODO Criar Exception específica
			throw new RuntimeException();
		} else {
			return lista;
		}
	}

	public Collection<Lista> getListaByTipo(int userId, int tipoLista) {
		// TODO Auto-generated method stub
		return null;
	}

}
