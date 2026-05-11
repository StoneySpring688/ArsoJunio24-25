package com.um.Control.config;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {
	public static final String URI = "amqp://guest:guest@localhost:5672"; 
	public static final String EXCHANGE = "amq.direct";
	public static final String ROUTINGKEY = "acceso";
	public static final String QUEUE = "accesos";
	
	private static final String HOST = "localhost";
    private static final int PORT = 5672;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String VIRTUAL_HOST = "/";
    private static final boolean USE_SSL = false;
	
	/**
     * Crea y devuelve una ConnectionFactory configurada con las credenciales de RabbitMQ.
     * En Docker se usa sin SSL (RabbitMQ local).
     */
    public static ConnectionFactory crearConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        if (USE_SSL) {
            try {
                factory.useSslProtocol();
            } catch (Exception e) {
                throw new RuntimeException("Error al configurar SSL para RabbitMQ", e);
            }
        }
        return factory;
    }
}
