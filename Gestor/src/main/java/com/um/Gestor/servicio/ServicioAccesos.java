package com.um.Gestor.servicio;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.um.Gestor.dto.AltaAccesoDTO;
import com.um.Gestor.puertos.PuertoEntradaEventos;
import com.um.Gestor.repositorio.RepositorioAcceso;

@Service
@Transactional
public class ServicioAccesos implements IServicioAccesos, PuertoEntradaEventos {
	
	private final RepositorioAcceso repositorio;
	
	@Autowired
	public ServicioAccesos(RepositorioAcceso repositorio) {
		this.repositorio = repositorio;
	}
	
	@Override
	public Page<AltaAccesoDTO> getAccesosUsuario(String userId, Pageable pageable) {
		return repositorio.findByUserIdOrderByFechaHoraAccesoDesc(userId, pageable);
	}

	@Override
	public Page<AltaAccesoDTO> getAccesosEntreFechas(LocalDate inicio, LocalDate fin, Pageable pageable) {
		return repositorio.findByFechaHoraAccesoBetween(inicio, fin, pageable);
	}

	@Override
	public void altaAcceso(AltaAccesoDTO acceso) {
		repositorio.save(acceso);
	}

}
