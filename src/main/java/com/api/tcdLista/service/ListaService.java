package com.api.tcdLista.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tcdLista.model.Lista;
import com.api.tcdLista.model.ListaConteudo;
import com.api.tcdLista.model.ListaConteudoDTO;
import com.api.tcdLista.repository.ListaConteudoRepository;
import com.api.tcdLista.repository.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository listaRepository;

	@Autowired
	private ListaConteudoRepository listaConteudoRepository;

	public ListaConteudoDTO getListas(int userId) {
		Collection<Lista> listas = listaRepository.findByUserId(userId);

		ListaConteudoDTO listaComConteudo = new ListaConteudoDTO();
		//Collection<ListaConteudo> listaAux = new ArrayList<ListaConteudo>();
		Collection<Long> myList = new ArrayList<Long>();
		Collection<Long> assistidos = new ArrayList<Long>();
		for (Lista lista : listas) {

			Collection<ListaConteudo> listaNova = new ArrayList<ListaConteudo>();
			listaNova = listaConteudoRepository.findByLista(lista);
			
			
			for (ListaConteudo listaConteudo : listaNova) {
				if(lista.getTipoLista().getId() == 1) {
					
					myList.add(listaConteudo.getIdConteudo());
					
				} else if(lista.getTipoLista().getId() == 2) {
					assistidos.add(listaConteudo.getIdConteudo());
				}
			}
			
			listaComConteudo.setMyList(myList);
			listaComConteudo.setAssistidos(assistidos);
		}

		return listaComConteudo;

	}

	public ListaConteudo getListaByTipo(int userId, int tipoLista) {
		
		return null;
//		Collection<ListaConteudo> listas = getListas(userId);
//		ListaConteudo listaBuscada = null;
//
//		if (listas.isEmpty()) {
//			// TODO Criar Exception específica
//			throw new RuntimeException();
//		} else {
//			for (ListaConteudo item : listas) {
//				if (item.getLista().getTipoLista().getId() == tipoLista) {
//					listaBuscada = item;
//
//					return listaBuscada;
//				}
//			}
//		}
//		// TODO Criar Exception: User não possui lista do tipo solicitado
//		throw new RuntimeException();
	}

	public void updateLista(int userId, int tipoLista, int idConteudo, int action) {
		if (action == 1) {
			// Lista lista = getListaByTipo(userId, tipoLista); //Pegando a lista que será
			// alterada
			// if (lista.)
			// TODO Verificar se o idConteudo já está na idLista
			// TODO Inserir o idConteudo e idLista na ListaConteudo
			// TODO Gravar ListaConteudo no BD
		} else if (action == 2) {
			// TODO Encontrar o id da lista de acordo com userID e tipoLista
			// TODO Verificar se o idConteudo existe na lista
			// TODO Remover o idConteudo da tabela lista_conteudo

		} else {
			// TODO Criar Exception específica
			throw new RuntimeException();
		}

	}
}
