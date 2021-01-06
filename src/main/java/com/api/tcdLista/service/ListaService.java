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

	public Collection<ListaConteudoDTO> getListas(int userId) {
		Collection<Lista> userLists = listaRepository.findByUserId(userId);

		// Criação dos DTOs que receberão a lista de conteúdos e tipo da lista
		ListaConteudoDTO listaMyList = new ListaConteudoDTO();
		listaMyList.setTipoLista(1);
		ListaConteudoDTO listaAssistidos = new ListaConteudoDTO();
		listaAssistidos.setTipoLista(2);

		// Criação da lista de conteúdos
		Collection<Long> myList = new ArrayList<Long>();
		Collection<Long> assistidos = new ArrayList<Long>();

		// Criação da lista de retorno do serviço
		Collection<ListaConteudoDTO> listaCompleta = new ArrayList<ListaConteudoDTO>();

		for (Lista lista : userLists) {

			// Busca o conteúdo de uma lista do usuário
			Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
			conteudos = listaConteudoRepository.findByLista(lista);

			for (ListaConteudo listaConteudo : conteudos) {
				if (lista.getTipoLista().getId() == 1) {
					myList.add(listaConteudo.getIdConteudo());

				} else if (lista.getTipoLista().getId() == 2) {
					assistidos.add(listaConteudo.getIdConteudo());
				}
			}

			listaMyList.setConteudos(myList);
			listaAssistidos.setConteudos(assistidos);
		}

		listaCompleta.add(listaMyList);
		listaCompleta.add(listaAssistidos);

		return listaCompleta;
	}

	public ListaConteudoDTO getListaByTipo(int userId, int tipoLista) {
		Collection<Lista> userLists = listaRepository.findByUserId(userId);
		Collection<Long> listaConteudos = new ArrayList<Long>();
		ListaConteudoDTO listaBuscada = new ListaConteudoDTO();

		for (Lista lista : userLists) {
			if (lista.getTipoLista().getId() == tipoLista) {
				Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
				conteudos = listaConteudoRepository.findByLista(lista);
				for (ListaConteudo conteudo : conteudos) {
					listaConteudos.add(conteudo.getIdConteudo());
				}
			}
		}

		listaBuscada.setTipoLista(tipoLista);
		listaBuscada.setConteudos(listaConteudos);

		return listaBuscada;
	}

	public void adicionaConteudo(int userId, int tipoLista, long idConteudo) {

		Collection<Lista> userLists = listaRepository.findByUserId(userId);

		for (Lista lista : userLists) {
			if (lista.getTipoLista().getId() == tipoLista) {
				Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
				conteudos = listaConteudoRepository.findByLista(lista);

				ListaConteudo listaConteudo = new ListaConteudo();
				listaConteudo.setIdLista(lista);
				listaConteudo.setIdConteudo(idConteudo);

				conteudos.add(listaConteudo);

				listaConteudoRepository.saveAll(conteudos);
			}
		}
	}

	public void removeConteudo(int userId, int tipoLista, long idConteudo) {

		Collection<Lista> userLists = listaRepository.findByUserId(userId);

		for (Lista lista : userLists) {
			if (lista.getTipoLista().getId() == tipoLista) {
				Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
				conteudos = listaConteudoRepository.findByLista(lista);

				ListaConteudo listaConteudo = new ListaConteudo();
				listaConteudo.setIdLista(lista);
				listaConteudo.setIdConteudo(idConteudo);
				
				for (ListaConteudo conteudo : conteudos) {
					if (conteudo.getIdConteudo() == idConteudo) {
						conteudos.remove(conteudo);
						break;
					}
				}

				listaConteudoRepository.saveAll(conteudos);
			}
		}
	}
}
