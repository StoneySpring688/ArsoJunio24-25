package com.um.Gestor.servicio;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.um.Gestor.dto.AltaAccesoDTO;

public interface IServicioAccesos {
	
	Page<AltaAccesoDTO> getAccesosUsuario(String userId, Pageable pageable);
	
	Page<AltaAccesoDTO> getAccesosEntreFechas(LocalDate inicio, LocalDate fin, Pageable pageable);
}
