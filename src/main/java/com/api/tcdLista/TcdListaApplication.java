package com.api.tcdLista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.api.tcdLista.model")
@EnableJpaRepositories("com.api.tcdLista.repository")
@ComponentScan("com.api.tcdLista.controller")
@ComponentScan("com.api.tcdLista.service")
@EnableDiscoveryClient
@SpringBootApplication
public class TcdListaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcdListaApplication.class, args);
	}
}
