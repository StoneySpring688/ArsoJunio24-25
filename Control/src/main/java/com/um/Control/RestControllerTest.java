package com.um.Control;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;

public class RestControllerTest {

	private static final String BASE_URI = "http://localhost:8080/api/";

	public static void main(String[] args) {
		ResourceConfig config = new ResourceConfig()
                .packages("com.um.Control.rest")
                .register(JacksonFeature.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI), config);

        System.out.println("Servidor arrancado en " + BASE_URI);
	}
}
