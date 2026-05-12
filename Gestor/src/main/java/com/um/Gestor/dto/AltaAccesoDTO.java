package com.um.Gestor.dto;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "accesos")
public class AltaAccesoDTO {
	
	@Id
	private String id;
	private String tipo; 
	private String userId;
	private int monitorId;
	private LocalDate fechaHoraAcceso;

	public AltaAccesoDTO(String id, String tipo, String userId, int monitorId, LocalDate fechaHoraAcceso) {
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

	public String getTipo() {
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

	public void setTipo(String tipo) {
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
