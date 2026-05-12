package com.um.Gestor.adaptadores;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.um.Gestor.config.RabbitMQConfig;
import com.um.Gestor.puertos.PuertoSalidaEventos;

@Component
public class RabbitMQProductorAdapter implements PuertoSalidaEventos {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void emitirEvento(Object evento) {
		
		rabbitTemplate.convertAndSend(
				RabbitMQConfig.EXCHANGE_NAME,
				RabbitMQConfig.ROUTING_KEY,
				evento);
		
	}
	
	
}
