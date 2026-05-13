package com.um.Control;

import com.um.Control.adaptadores.retrofit.AdaptadorGestorRetrofit;
import com.um.Control.puertos.PuertoGestor;

public class RetrofitTest {
	
	public static void main(String[] args) {
		PuertoGestor puerto = new AdaptadorGestorRetrofit();
		System.out.println(puerto.obtenerAccesoPorUsuario("usuario-arso").toString());
	}
}
