package com.um.Gestor.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static class RespuestaError {
		private String estado;
		private String mensaje;

		public RespuestaError(String estado, String mensaje) {
			this.estado = estado;
			this.mensaje = mensaje;
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleGlobalException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
