package com.um.Control.adaptadores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.um.Control.config.RabbitMQConfig;
import com.um.Control.dominio.Acceso;
import com.um.Control.puertos.EventosPort;
import com.um.Control.utils.JsonUtils;

public class RabbitMQProductorAdapter implements EventosPort {
	
	private final ObjectMapper objectMapper = JsonUtils.getMapper();

	@Override
	public void altaAcceso(Acceso acceso) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(RabbitMQConfig.URI);
			
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			byte[] jsonMsg = objectMapper.writeValueAsBytes(acceso);
			
			AMQP.BasicProperties props = new AMQP.BasicProperties().builder()
					.contentType("application/json")
					.build();
			
			channel.basicPublish(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTINGKEY, props, jsonMsg);
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
