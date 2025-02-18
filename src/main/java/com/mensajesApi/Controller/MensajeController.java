package com.mensajesApi.Controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mensajesApi.domain.mensaje.DatosListarMensaje;
import com.mensajesApi.domain.mensaje.DatosMensaje;
import com.mensajesApi.domain.mensaje.Mensaje;
import com.mensajesApi.domain.mensaje.MensajeRepository;
import com.mensajesApi.domain.mensaje.ValidationException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/mensaje")
public class MensajeController {
	
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@GetMapping
	public ResponseEntity<Page<DatosListarMensaje>> listarMensajes(@PageableDefault(size = 20) Pageable pageable){
		
		return ResponseEntity.ok(mensajeRepository.findAll(pageable).map(DatosListarMensaje::new));
	}
	
	@PostMapping
	public ResponseEntity<DatosMensaje> crear(@RequestBody @Valid DatosMensaje datosMensaje, UriComponentsBuilder uriComponentsBuilder){
		
		Mensaje mensaje = mensajeRepository.save(new Mensaje(datosMensaje));
		URI url = uriComponentsBuilder.path("/mensaje/{id}").buildAndExpand(mensaje.getId()).toUri();
		return ResponseEntity.created(url).body(new DatosMensaje(mensaje));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosMensaje> buscar(@PathVariable Long id){

		Mensaje mensaje = mensajeRepository.findById(id).orElse(null);
		if (mensaje==null) {
			throw new ValidationException("El mensaje no existe en la base de datos");
		}
		return ResponseEntity.ok(new DatosMensaje(mensaje));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity eliminar(@PathVariable("id") Long id){
		Mensaje mensaje = mensajeRepository.findById(id).orElse(null);
		if (mensaje == null) {
			throw new ValidationException("El mensaje no existe en la base de datos");
		}
		mensajeRepository.delete(mensaje);
		return ResponseEntity.noContent().build();
	}
	
	

}
