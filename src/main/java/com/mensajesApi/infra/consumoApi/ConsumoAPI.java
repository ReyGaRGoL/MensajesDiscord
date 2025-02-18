package com.mensajesApi.infra.consumoApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mensajesApi.domain.mensaje.DatosMensaje;

@Service
public class ConsumoAPI {

	@Value("${api.bot.token}")
	private String token;

	public String obtenerDatos(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		String json = response.body();
		return json;
	}

	public String enviarMensaje(DatosMensaje mensaje) {

		HttpClient client = HttpClient.newHttpClient();

		String jsonBody = "{ \"content\": \"" + mensaje.texto() + "\" }";

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://discord.com/api/v10/channels/" + mensaje.canal() + "/messages"))
				.header("Authorization", "Bot " + token).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return response.statusCode() + "";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "401";

	}

	public String listarServers() {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://discord.com/api/v10/users/@me/guilds"))
				.header("Authorization", "Bot " + token).header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		return response.body();
	}

	public String listarChannels(String idServer) {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://discord.com/api/v10/guilds/" + idServer + "/channels"))
				.header("Authorization", "Bot " + token).header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		return response.body();
	}
}
