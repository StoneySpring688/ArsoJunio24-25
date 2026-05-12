package com.um.Gestor.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.um.Gestor.dto.AltaAccesoDTO;

@Repository
public interface RepositorioAccesoMongo extends RepositorioAcceso, MongoRepository<AltaAccesoDTO, String> {
	/**
	 * Springboot deduce las consultas en base al nombrado de los métodos,
	 * en base a la convención de nombrado de SpringData.
	 **/
}
