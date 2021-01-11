package com.api.tcdLista.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.api.tcdLista.exception.ConteudoAlreadyExistsException;
import com.api.tcdLista.exception.ConteudoNotPresentException;
import com.api.tcdLista.exception.InvalidRequestException;
import com.api.tcdLista.exception.InvalidUserException;
import com.api.tcdLista.model.Lista;
import com.api.tcdLista.model.ListaConteudo;
import com.api.tcdLista.model.ListaConteudoDTO;
import com.api.tcdLista.model.TipoLista;
import com.api.tcdLista.model.UpdateRequestModel;
import com.api.tcdLista.repository.ListaConteudoRepository;
import com.api.tcdLista.repository.ListaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListaService {

	@Autowired
	private ListaRepository listaRepository;

	@Autowired
	private ListaConteudoRepository listaConteudoRepository;

	public Collection<ListaConteudoDTO> getListas(int userId) {

		if (userId > 0) {
			Collection<Lista> userLists = listaRepository.findByUserId(userId);

			// Criação dos DTOs que receberão a lista de conteúdos e tipo da lista
			ListaConteudoDTO listaMyList = new ListaConteudoDTO();
			listaMyList.setTipoLista(1);
			ListaConteudoDTO listaAssistidos = new ListaConteudoDTO();
			listaAssistidos.setTipoLista(2);
			ListaConteudoDTO listaCurtidos = new ListaConteudoDTO();
			listaCurtidos.setTipoLista(3);

			// Criação da lista de conteúdos
			Collection<Long> myList = new ArrayList<Long>();
			Collection<Long> assistidos = new ArrayList<Long>();
			Collection<Long> curtidos = new ArrayList<Long>();

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

					} else if (lista.getTipoLista().getId() == 3) {
						curtidos.add(listaConteudo.getIdConteudo());
					}
				}

				listaMyList.setConteudos(myList);
				listaAssistidos.setConteudos(assistidos);
				listaCurtidos.setConteudos(curtidos);
			}
			
			listaCompleta.add(listaMyList);
			listaCompleta.add(listaAssistidos);
			listaCompleta.add(listaCurtidos);

			return listaCompleta;
		}
		throw new InvalidUserException();
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

	@KafkaListener(topics = "listaAdd", groupId = "listaAdd")
	public void adicionaConteudo(String requestParam) {
		ObjectMapper objectMapper = new ObjectMapper();

		UpdateRequestModel request;
		try {
			request = objectMapper.readValue(requestParam, UpdateRequestModel.class);

			if (request.getUserId() > 0 && request.getIdConteudo() > 0 && request.getTipoLista() > 0
					&& request.getTipoLista() < 4) {

				Collection<Lista> userLists = listaRepository.findByUserId(request.getUserId());

				if (userLists.isEmpty()) {
					TipoLista tipo1 = new TipoLista(1);
					TipoLista tipo2 = new TipoLista(2);
					TipoLista tipo3 = new TipoLista(3);
					
					Lista novaLista1 = new Lista(request.getUserId(), tipo1);
					Lista novaLista2 = new Lista(request.getUserId(), tipo2);
					Lista novaLista3 = new Lista(request.getUserId(), tipo3);

					listaRepository.save(novaLista1);
					listaRepository.save(novaLista2);
					listaRepository.save(novaLista3);
					
					userLists.add(novaLista1);
					userLists.add(novaLista2);
					userLists.add(novaLista3);
				}

				for (Lista lista : userLists) {
					if (lista.getTipoLista().getId() == request.getTipoLista()) {
						Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
						conteudos = listaConteudoRepository.findByLista(lista);

						for (ListaConteudo conteudo : conteudos) {
							if (conteudo.getIdConteudo() == request.getIdConteudo()) {
								throw new ConteudoAlreadyExistsException();
							}
						}

						ListaConteudo listaConteudo = new ListaConteudo();
						listaConteudo.setIdLista(lista);
						listaConteudo.setIdConteudo(request.getIdConteudo());

						conteudos.add(listaConteudo);

						listaConteudoRepository.saveAll(conteudos);
						return;
					}
				}
			}
			throw new InvalidRequestException();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "listaRemove", groupId = "listaRemove")
	public void removeConteudo(String requestParam) {
		ObjectMapper objectMapper = new ObjectMapper();

		UpdateRequestModel request;

		try {
			request = objectMapper.readValue(requestParam, UpdateRequestModel.class);

			if (request.getUserId() > 0 && request.getIdConteudo() > 0 && request.getTipoLista() > 0
					&& request.getTipoLista() < 4) {

				Collection<Lista> userLists = listaRepository.findByUserId(request.getUserId());

				for (Lista lista : userLists) {
					if (lista.getTipoLista().getId() == request.getTipoLista()) {
						Collection<ListaConteudo> conteudos = new ArrayList<ListaConteudo>();
						conteudos = listaConteudoRepository.findByLista(lista);

						for (ListaConteudo conteudo : conteudos) {
							if (conteudo.getIdConteudo() == request.getIdConteudo()) {
								long idListaConteudo = conteudo.getIdListaConteudo();
								listaConteudoRepository.deleteById(idListaConteudo);
								return;
							}
						}
						throw new ConteudoNotPresentException();
					}
				}
			}
			throw new InvalidRequestException();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
