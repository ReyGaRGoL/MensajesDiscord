package com.mensajesApi.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mensajesApi.domain.channel.DatosChannel;
import com.mensajesApi.domain.mensaje.DatosListarMensaje;
import com.mensajesApi.domain.mensaje.DatosMensaje;
import com.mensajesApi.domain.server.DatosServer;
import com.mensajesApi.infra.consumoApi.ConsumoAPI;
import com.mensajesApi.infra.consumoApi.ConvierteDatos;

@Controller
public class MainController {

	@Autowired
	private ConsumoAPI consumoAPI= new ConsumoAPI();
	
	@Autowired MensajeController mensajeController;
	
	private ConvierteDatos convierteDatos = new ConvierteDatos();
	
	@GetMapping("/")
    public String showServers(@RequestParam(required = false) Long serverId,Model model, Pageable pageable) {
       
        	ResponseEntity<Page<DatosListarMensaje>> response = mensajeController.listarMensajes(pageable);
        	model.addAttribute("mensajesProgramados", response.getBody().toList());
        	
        return "index"; 
    }
	
	@GetMapping("/programarMensaje")
    public String programarMensaje(@RequestParam(required = false) Long serverId,Model model, Pageable pageable) {
        try {
        	
        	
        	String json = consumoAPI.listarServers();
            var servers = convierteDatos.obtenerDatos(json, new TypeReference<List<DatosServer>>() {});
            model.addAttribute("servers", servers);
            
            List<DatosChannel> channels;
            
            if (serverId != null) {
            	String jsonChannels = consumoAPI.listarChannels(serverId+"");
            	channels= convierteDatos.obtenerDatos(jsonChannels, new TypeReference<List<DatosChannel>>() {}) ;
            	channels= channels.stream().filter(c -> c.type()==0).collect(Collectors.toList());
            	
			}else {
				channels= new ArrayList<>();
			}
            
            model.addAttribute("channels", channels);
            
            model.addAttribute("selectedServerId", serverId+"");
            
            LocalDateTime now = LocalDateTime.now();
            String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            model.addAttribute("fechaActual", formattedNow);
            
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los datos. Verifica tus variables de entorno.");
        }
        return "programarMensaje"; 
    }
	
	@PostMapping("/procesarEnvio")
	public String procesarEnvio(@ModelAttribute DatosMensaje datosMensaje, UriComponentsBuilder uriComponents, Model model) {
		ResponseEntity<DatosMensaje> response = mensajeController.crear(datosMensaje, uriComponents);
		if (response.getStatusCode()!=HttpStatusCode.valueOf(201)) {
			model.addAttribute("error",response.getBody());
	        return"redirect:/programarMensaje";
		}

		return"redirect:/";
	}
	
	@PostMapping("/eliminarMensaje")
	public String eliminarMensaje(@RequestParam Long id, Model model) {
		ResponseEntity<DatosMensaje> response = mensajeController.eliminar(id);
		if (response.getStatusCode()!=HttpStatusCode.valueOf(204)) {
			model.addAttribute("error",response.getBody());
		}

        return"redirect:/";
	}
	
	@GetMapping("/verMensaje/{id}")
	public String verMensaje(@PathVariable Long id, Model model) {
		ResponseEntity<DatosMensaje> response = mensajeController.buscar(id);
		if (response.getStatusCode()!=HttpStatusCode.valueOf(200)) {
			model.addAttribute("error",response.getBody());
	        return"redirect:/";
		}
		
		model.addAttribute("mensaje",response.getBody());
        return"verMensaje";
	}
	
}
