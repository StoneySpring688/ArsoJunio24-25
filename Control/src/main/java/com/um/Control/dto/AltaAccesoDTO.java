package com.um.Control.dto;

import java.time.LocalDate;
import com.um.Control.dominio.TipoAcceso;

/**
 * En principio no es necesario, pero se agrega por ser la "forma estandar" de comunicar el dominio*/
public class AltaAccesoDTO {
	
	private String id;
	private TipoAcceso tipo; // Para que jackson interprete el enum correctamente debe hacerse como en TipoAcceso (verlo para ver como indicar a jackson como pasar de String a enum y viceversa)
	private String userId;
	private int monitorId;
	private LocalDate fechaHoraAcceso;

	public AltaAccesoDTO(String id, TipoAcceso tipo, String userId, int monitorId, LocalDate fechaHoraAcceso) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.userId = userId;
		this.monitorId = monitorId;
		this.fechaHoraAcceso = fechaHoraAcceso;
	}
	
	public AltaAccesoDTO() {}
	
	public String getId() {
		return id;
	}

	public TipoAcceso getTipo() {
		return tipo;
	}

	public String getUserId() {
		return userId;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public LocalDate getFechaHoraAcceso() {
		return fechaHoraAcceso;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTipo(TipoAcceso tipo) {
		this.tipo = tipo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public void setFechaHoraAcceso(LocalDate fechaHoraAcceso) {
		this.fechaHoraAcceso = fechaHoraAcceso;
	}
	
}
