package com.api.tcdLista.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.tcdLista.model.ListaConteudo;
import com.api.tcdLista.model.ListaConteudoDTO;
import com.api.tcdLista.service.ListaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Listas")
@RestController
@ComponentScan("com.api.tcdLista.service")
@RequestMapping(value = "v1/lista")
public class ListaController {

	@Autowired
	private ListaService listaService;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/service-instances/{applicationName}", method = RequestMethod.GET)
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@ApiOperation(value = "Retorna todas as listas de um usuário")
	@GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListaConteudoDTO getListas(@PathVariable("userId") int userId) {
		return listaService.getListas(userId);
	}

	@ApiOperation(value = "Retorna uma lista de um tipo específico do usuário")
	@GetMapping(value = "{userId}/{tipoLista}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListaConteudo getListaByTipo(@PathVariable("userId") int userId, @PathVariable("tipoLista") int tipoLista) {
		return listaService.getListaByTipo(userId, tipoLista);
	}
	
	@ApiOperation(value = "Adiciona um conteúdo em uma lista")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateConteudo(int userId, int tipoLista, int idConteudo, int action) {
		listaService.updateLista(userId, tipoLista, idConteudo, action);
	}
}
