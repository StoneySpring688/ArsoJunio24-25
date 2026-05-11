package com.um.Control.rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.um.Control.dto.AltaAccesoDTO;
import com.um.Control.servicio.FactoriaServicios;
import com.um.Control.servicio.IServicioAcceso;

@Path("/accesos")
public class RestController {

	private IServicioAcceso servicio;

	@Context
	private UriInfo uriInfo;

	public RestController() {
		try {
			this.servicio = FactoriaServicios.getServicio(IServicioAcceso.class);
		} catch (Exception e) {
			System.err.println("ERROR AL CARGAR SERVICIO: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	public String test() {
		return "API funcionando";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response altaAcceso(AltaAccesoDTO accesoDTO) {
		servicio.altaAcceso(accesoDTO);
		return Response.status(Response.Status.CREATED).build();
	}
}
