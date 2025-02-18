package com.mensajesApi.domain.server;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mensajesApi.domain.channel.DatosChannel;

public record DatosListarServer(
		
		@JsonAlias("id")
		String id,
		@JsonAlias("name")
		String name,
		
		List<DatosChannel> channels
		
		) {

}
