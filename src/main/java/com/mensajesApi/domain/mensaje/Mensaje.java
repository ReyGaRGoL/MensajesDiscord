package com.mensajesApi.domain.mensaje;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Mensaje")
@Table(name = "mensajes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Mensaje {

	
	public Mensaje(DatosMensaje datosMensaje) {

		this.texto=datosMensaje.texto();
		this.fechaEnvio=datosMensaje.fechaEnvio();
		this.fechaCreacion=LocalDateTime.now();
		this.canal=datosMensaje.canal();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String canal;
	private String texto;
	private LocalDateTime fechaEnvio;
	private LocalDateTime fechaCreacion;
	
	
	
	
}
