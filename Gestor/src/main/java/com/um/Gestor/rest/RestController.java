package com.um.Gestor.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.Gestor.dto.AltaAccesoDTO;
import com.um.Gestor.servicio.IServicioAccesos;


@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/gestor", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {
	private IServicioAccesos servicio;
	private PagedResourcesAssembler<AltaAccesoDTO> pagedResourcesAssembler;
	
	
	@Autowired
	public RestController(IServicioAccesos servicio, 
			PagedResourcesAssembler<AltaAccesoDTO> pagedResourcesAssembler) {
		this.servicio = servicio;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}
	
	@GetMapping("/{id}")
	public PagedModel<EntityModel<AltaAccesoDTO>> getAccesoByUserId(@PathVariable String id, Pageable pageable) {
		Page<AltaAccesoDTO> acceso = servicio.getAccesosUsuario(id, pageable); 
		return this.pagedResourcesAssembler.toModel(acceso);
	}
}
