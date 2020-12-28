package com.api.tcdLista.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.api.tcdLista.model.Lista;

@Repository
@Component
public interface ListaRepository extends JpaRepository<Lista, Integer> {
	
	Collection<Lista> findByUserId(int userId);

}
