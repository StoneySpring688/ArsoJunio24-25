package com.um.Control.servicio;

import com.um.Control.dto.AltaAccesoDTO;
import com.um.Control.puertos.PuertoSalidaEventos;

public class ServicioAcceso implements IServicioAcceso {
	private PuertoSalidaEventos puertoSalidaEventos = FactoriaServicios.getServicio(PuertoSalidaEventos.class);
	
	public void altaAcceso(AltaAccesoDTO acceso) {
		puertoSalidaEventos.altaAcceso(acceso);
	}
}
