package com.um.Control.adaptadores.retrofit;

import com.um.Control.dto.HATEOASResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GestorRestClient {
	
	@GET("gestor/{id}")
	Call<HATEOASResponse> getAccesoPorUsuario(@Path("id") String id);
}
