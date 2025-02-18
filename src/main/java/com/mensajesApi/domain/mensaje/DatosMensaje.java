package com.mensajesApi.domain.mensaje;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosMensaje(
		
		@NotBlank(message = "no se permiten datos vacios")
        @NotNull(message = "ingrese un Canal")
		String canal,
		
		@NotBlank(message = "no se permiten datos vacios")
        @NotNull(message = "ingrese un titulo")
		String texto,
		
		@FutureOrPresent(message = "La fecha debe ser futura")
		LocalDateTime fechaEnvio
		) {

	public DatosMensaje(Mensaje mensaje) {
		this(mensaje.getCanal(),mensaje.getTexto(),mensaje.getFechaEnvio());
	}

}
