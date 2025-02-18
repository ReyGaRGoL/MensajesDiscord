package com.mensajesApi.domain.mensaje;

import java.time.LocalDateTime;

public record DatosListarMensaje(
		Long id,
		String canal,
		String texto,
		LocalDateTime fechaEnvio,
		LocalDateTime fechaCreacion
		) {
	
	public DatosListarMensaje(Mensaje mensaje) {
		this(mensaje.getId(),mensaje.getCanal(),mensaje.getTexto(),mensaje.getFechaEnvio(),mensaje.getFechaCreacion());
	}

}
