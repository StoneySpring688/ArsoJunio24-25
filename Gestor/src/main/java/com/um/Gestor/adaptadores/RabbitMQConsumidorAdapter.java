package com.um.Gestor.adaptadores;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.um.Gestor.config.RabbitMQConfig;
import com.um.Gestor.dto.AltaAccesoDTO;
import com.um.Gestor.puertos.PuertoEntradaEventos;

@Component
public class RabbitMQConsumidorAdapter {
	
	
	private final PuertoEntradaEventos puerto;
	
	@Autowired
	public RabbitMQConsumidorAdapter(PuertoEntradaEventos puerto) {
		this.puerto = puerto;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void handleEvent(AltaAccesoDTO acceso, @Header("amqp_receivedRoutingKey") String routingKey) {
		try {
			switch (routingKey) {
			case "acceso":
				puerto.altaAcceso(acceso);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
