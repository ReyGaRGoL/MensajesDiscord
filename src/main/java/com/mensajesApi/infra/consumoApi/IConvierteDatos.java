package com.mensajesApi.infra.consumoApi;

import com.fasterxml.jackson.core.type.TypeReference;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, TypeReference<T> typeReference);
}
