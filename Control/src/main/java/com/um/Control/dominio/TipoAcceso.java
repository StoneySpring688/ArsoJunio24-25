package com.um.Control.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoAcceso {
	ENTRADA("entrada"), 
	SALIDA("salida");

	private String valor;

	private TipoAcceso(String valor) {
		this.valor = valor;
	}

	@JsonValue // Indica que para JSON este es el valor que representa al enum
	public String getValor() {
		return valor;
	}

	@JsonCreator // Indica cómo crear el enum desde un String del JSON
	public static TipoAcceso fromString(String nombre) {
		for (TipoAcceso tipo : TipoAcceso.values()) {
			if (tipo.valor.equalsIgnoreCase(nombre)) {
				return tipo;
			}
		}
		return null;
	}

}
