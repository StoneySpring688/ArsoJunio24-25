package com.um.Gestor;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.um.Gestor.dto.AltaAccesoDTO;
import com.um.Gestor.puertos.PuertoSalidaEventos;

@SpringBootApplication
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(PuertoSalidaEventos puertoEventos) {
		return args -> {

			// 1. Crear el DTO de prueba
			AltaAccesoDTO prueba = new AltaAccesoDTO();
			prueba.setId("test-ioc-002");
			prueba.setUserId("usuario-arso");
			prueba.setTipo("ENTRADA");
			prueba.setMonitorId(42);
			prueba.setFechaHoraAcceso(LocalDate.now());

			System.out.println(">>> Publicando evento a través del Puerto de Salida...");

			/*
			 * 2. Usar el puerto mediante IoC. Spring inyectará automáticamente el
			 * 'RabbitMQProductorAdapter' porque está marcado con @Component.
			 */
			puertoEventos.emitirEvento(prueba);

			System.out.println(">>> Evento enviado con éxito desde el adaptador.");
		};
	}
}
