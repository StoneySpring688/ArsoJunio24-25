package com.um.Control.rest;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")  
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Mismo secreto que la pasarela y el JwtTokenFilter para que los tokens sean compatibles
    public static final String SECRET_KEY = "secreto_compartido_2026";
    private final String ADMINPASSWD = "arso-2025";

    public AuthController() {}

    @POST
    @Path("/login")
    @PermitAll
    public Response login(
            @FormParam("monitorId") int monitorId,
            @FormParam("clave") String clave) {
    	
    	logger.info("Recibida petición de login (JWT) para el id: {} y clave {}", monitorId, clave);
    	
    	try {
    		if(clave.equals(ADMINPASSWD)) {
    			logger.debug("Login correcto en servicio para el ID: {}", monitorId);
    			
    			// Construir los claims del token
                Map<String, Object> claims = new HashMap<>();
                claims.put("sub", monitorId);
                claims.put("roles", Arrays.asList("ADMIN"));  

                // Fecha de caducidad: 10 díaspasarela)
                Date caducidad = Date.from(
                        Instant.now().plusSeconds(864000));

                // Generar el token JWT con HS512
                String token = Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                        .setExpiration(caducidad)
                        .compact();
                
                logger.info("Token JWT generado exitosamente para el usuario: {}", monitorId);

                return Response.ok(token).build();
    		}else {
    			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Password").build();
    		}

        } catch (Exception e) {
            logger.error("Error inesperado durante el proceso de login JWT: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno del servidor").build();
        }
    }
}
