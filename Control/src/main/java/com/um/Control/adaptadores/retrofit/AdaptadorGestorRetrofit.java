package com.um.Control.adaptadores.retrofit;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.um.Control.adaptadores.LocalDateAdapter;
import com.um.Control.dto.AltaAccesoDTO;
import com.um.Control.dto.HATEOASResponse;
import com.um.Control.puertos.PuertoGestor;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorGestorRetrofit implements PuertoGestor {
	
	private final GestorRestClient api;
	
	public AdaptadorGestorRetrofit() {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8081/api/")
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		this.api = retrofit.create(GestorRestClient.class);
	}

	@Override
	public List<AltaAccesoDTO> obtenerAccesoPorUsuario(String id) {
		List<AltaAccesoDTO> altas = Collections.emptyList();
		try {
			Response<HATEOASResponse> response = api.getAccesoPorUsuario(id).execute();
			if(response.isSuccessful() && response.body() != null) {
				HATEOASResponse body = response.body();
				if(body._embedded != null) {
					altas = body._embedded.altas;
				}
			}
			return altas;
		} catch (Exception e) {
			System.err.println("Error al obtener las altas de usuario " + id + " via Retrofit " + e.getMessage());
			throw new RuntimeException("Error en Retrofit", e);
			
		}
	}
}
