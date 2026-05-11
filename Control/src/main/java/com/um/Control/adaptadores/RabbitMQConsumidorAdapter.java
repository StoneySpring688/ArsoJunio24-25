package com.um.Control.adaptadores;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.um.Control.config.RabbitMQConfig;

public class RabbitMQConsumidorAdapter {
	
	private static RabbitMQConsumidorAdapter instance;
	private Channel channel;
	
	private RabbitMQConsumidorAdapter() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(RabbitMQConfig.URI);
			
			Connection connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RabbitMQConsumidorAdapter getInstance() {
        if (instance == null) {
            instance = new RabbitMQConsumidorAdapter();
        }
        return instance;
    }
	
	public void iniciarConsumo() {
        boolean autoAck = false;
        
        try {
        	channel.basicConsume(RabbitMQConfig.QUEUE, autoAck, RabbitMQConfig.ROUTINGKEY,
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope,
                                                   AMQP.BasicProperties properties, byte[] body) 
                                                		   throws IOException {
                        	String routingKey = envelope.getRoutingKey();
                        	String contentType = properties.getContentType();
                        	long deliveryTag = envelope.getDeliveryTag();
                        	
                        	String contenido = new String(body);
                        	System.out.println(contenido);
                        	
                        	channel.basicAck(deliveryTag, false);
                        }
                    });
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
}
