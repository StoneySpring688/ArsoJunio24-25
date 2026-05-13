package com.um.Control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.rabbitmq.client.ConnectionFactory;
import com.um.Control.adaptadores.RabbitMQConsumidorAdapter;
import com.um.Control.adaptadores.RabbitMQProductorAdapter;
import com.um.Control.config.RabbitMQConfig;
import com.um.Control.dominio.TipoAcceso;
import com.um.Control.dto.AltaAccesoDTO;
import com.um.Control.puertos.PuertoSalidaEventos;
import com.um.Control.rest.RestController;

public class RabbitMQTest 
{
    public static void main( String[] args )
    {
        PuertoSalidaEventos port = new RabbitMQProductorAdapter();
        port.altaAcceso(
				new AltaAccesoDTO(UUID.randomUUID().toString(),
						TipoAcceso.ENTRADA, "usuario", 0, LocalDate.now())
				);
        
        ConnectionFactory connectionFactory = RabbitMQConfig.crearConnectionFactory();
        RabbitMQConsumidorAdapter consumidor = new RabbitMQConsumidorAdapter(connectionFactory);
        consumidor.iniciar();
        
        RestController rest = new RestController();
    }
}
