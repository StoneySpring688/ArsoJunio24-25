package com.um.Control.config;

public class RabbitMQConfig {
	public static final String URI = "amqp://guest:guest@localhost:5672"; 
	public static final String EXCHANGE = "amq.direct";
	public static final String ROUTINGKEY = "acceso";
	public static final String QUEUE = "accesos";
}
