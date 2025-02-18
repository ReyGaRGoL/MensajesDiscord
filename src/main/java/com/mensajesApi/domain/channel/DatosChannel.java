package com.mensajesApi.domain.channel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosChannel(
		
		@JsonAlias("id")
		String id,
		
		@JsonAlias("name")
		String name,
		
		@JsonAlias("guild_id")
		String idServer,
		
		@JsonAlias("type")
		Long type
		
		) {

}
