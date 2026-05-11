package com.um.Control.adaptadores;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.um.Control.config.RabbitMQConfig;

public class RabbitMQConsumidorAdapter {
	
	private static final Gson gson = new Gson();
	private final ConnectionFactory connectionFactory;
    //private final PuertoEntradaEventos puertoEntradaEventos;
	
    public RabbitMQConsumidorAdapter(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        //this.puertoEntradaEventos = puertoEntradaEventos;
    }
	
    /**
     * Arranca el consumidor y empieza a escuchar.
     * El exchange, las colas y los bindings se crean de forma centralizada mediante rabbitmq-definitons.json
     */
    public void iniciar() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String routingKey = delivery.getEnvelope().getRoutingKey();
                String cuerpo = new String(delivery.getBody(), StandardCharsets.UTF_8);

                try {
                    Map<String, String> mensaje = gson.fromJson(cuerpo, new TypeToken<Map<String, String>>(){}.getType());
                    recibirEvento(routingKey, mensaje);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            channel.basicConsume(RabbitMQConfig.QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void recibirEvento(String routingKey, Map<String, String> mensaje) {
    	try {
			switch (routingKey) {
			case RabbitMQConfig.ROUTINGKEY:
				System.out.println(mensaje.toString());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
