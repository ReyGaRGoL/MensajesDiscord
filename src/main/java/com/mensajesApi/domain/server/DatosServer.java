package com.mensajesApi.domain.server;

import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosServer(
		
		@JsonAlias("id")
		String id,
		@JsonAlias("name")
		String name
		
		) {
	
	

}
