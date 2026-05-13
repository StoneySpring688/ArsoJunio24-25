package com.um.Control.rest.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenFilter implements ContainerRequestFilter {
	
	public static final String SECRET_KEY = "secreto_compartido_2026";

	@Context
    private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if(resourceInfo.getResourceMethod().isAnnotationPresent(PermitAll.class)) {
			return;
		}
		
		String token = null;
		String authorization = requestContext.getHeaderString("Authorization");
		if(authorization != null && authorization.startsWith("Bearer ")) {
			token = authorization.substring("Bearer ".length()).trim();
		}
		
		if(token == null) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("No hay Token JWT").build());
		}else {
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET_KEY.getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			requestContext.setProperty("claims", claims);
			
			Set<String> roles = new HashSet<>();
			Object rolesObj = claims.get("roles");
			if(rolesObj instanceof List) {
				@SuppressWarnings("unchecked")
				List<String> rolesList = (List<String>) rolesObj;
				roles.addAll(rolesList);
			} else if(rolesObj instanceof String) {
				roles.addAll(Arrays.asList(((String) rolesObj).split(",")));
			}
			
			if(resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class)) {
				String[] allowedRoles = resourceInfo.getResourceMethod()
						.getAnnotation(RolesAllowed.class).value();
				if(roles.stream()
						.noneMatch(userRole -> Arrays.asList(allowedRoles)
								.contains(userRole))) {
					requestContext.abortWith(
							Response.status(Response.Status.FORBIDDEN).build());
				}
			}
		}

	}

}
