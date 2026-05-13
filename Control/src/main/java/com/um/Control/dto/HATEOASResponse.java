package com.um.Control.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HATEOASResponse {
	@SerializedName("_embedded")
	public Embedded _embedded;

	public class Embedded {
		@SerializedName("altaAccesoDTOList")
		public List<AltaAccesoDTO> altas;
	}
}
