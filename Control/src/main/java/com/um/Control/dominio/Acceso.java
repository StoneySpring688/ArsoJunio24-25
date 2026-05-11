package com.um.Control.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Acceso {
	private String id;
	private TipoAcceso tipo;
	private String userId;
	private int monitorId;
	private LocalDate fechaHoraAcceso;

	public Acceso(String id, TipoAcceso tipo, String userId, int monitorId, LocalDate fechaHoraAcceso) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.userId = userId;
		this.monitorId = monitorId;
		this.fechaHoraAcceso = fechaHoraAcceso;
	}

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
	
	
	
}
