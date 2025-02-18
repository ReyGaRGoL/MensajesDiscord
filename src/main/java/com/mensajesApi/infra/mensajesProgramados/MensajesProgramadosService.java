package com.mensajesApi.infra.mensajesProgramados;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mensajesApi.domain.mensaje.DatosMensaje;
import com.mensajesApi.domain.mensaje.Mensaje;
import com.mensajesApi.domain.mensaje.MensajeRepository;
import com.mensajesApi.infra.consumoApi.ConsumoAPI;

@Service
@EnableScheduling
public class MensajesProgramadosService {

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private ConsumoAPI consumoAPI = new ConsumoAPI();

	@Async
	@Scheduled(fixedRate = 1000)
	public void revisarMensajes() {
		List<Mensaje> mensajes = mensajeRepository.findAll();
		mensajes.forEach(m -> {
			if (m.getFechaEnvio() == LocalDateTime.now() || m.getFechaEnvio().isBefore(LocalDateTime.now())) {
				enviarMensaje(m);
				mensajeRepository.delete(m);
			}
		});
	}

	public void enviarMensaje(Mensaje mensaje) {

		consumoAPI.enviarMensaje(new DatosMensaje(mensaje));

	}

}
