package com.um.Gestor.repositorio;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.um.Gestor.dto.AltaAccesoDTO;

@NoRepositoryBean
public interface RepositorioAcceso extends PagingAndSortingRepository<AltaAccesoDTO, String> {
	
	/**
	 * Recuperar todos los accesos de un usuario 
	 * ordenados por fecha (primero los másrecientes).
	 **/
	Page<AltaAccesoDTO> findByUserIdOrderByFechaHoraAccesoDesc(String userId, Pageable pageable);
	
	/**
	 * Obtener los accesos en un rango de fechas. 
	 **/
	Page<AltaAccesoDTO> findByFechaHoraAccesoBetween(LocalDate inicio, LocalDate fin, Pageable pageable);
}
