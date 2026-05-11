package com.um.Control;

import java.time.LocalDateTime;
import java.util.UUID;

import com.um.Control.adaptadores.RabbitMQConsumidorAdapter;
import com.um.Control.adaptadores.RabbitMQProductorAdapter;
import com.um.Control.dominio.Acceso;
import com.um.Control.dominio.TipoAcceso;
import com.um.Control.puertos.EventosPort;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EventosPort port = new RabbitMQProductorAdapter();
        port.altaAcceso(
				new Acceso(UUID.randomUUID().toString(),
						TipoAcceso.entrada, "usuario", 0, LocalDateTime.now())
				);
        RabbitMQConsumidorAdapter consumer = RabbitMQConsumidorAdapter.getInstance();
        consumer.iniciarConsumo();
    }
}
