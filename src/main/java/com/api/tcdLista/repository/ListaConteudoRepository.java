package com.api.tcdLista.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.api.tcdLista.model.Lista;
import com.api.tcdLista.model.ListaConteudo;

@Repository
@Component
public interface ListaConteudoRepository extends JpaRepository<ListaConteudo, Long> {
	
	Collection<ListaConteudo> findByLista(Lista Lista);

}
