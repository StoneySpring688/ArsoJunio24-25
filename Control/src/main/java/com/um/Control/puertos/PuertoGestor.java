package com.um.Control.puertos;

import java.util.List;

import com.um.Control.dto.AltaAccesoDTO;

public interface PuertoGestor {
	List<AltaAccesoDTO> obtenerAccesoPorUsuario(String id);
}
