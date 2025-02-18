package com.mensajesApi.domain.mensaje;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long>{

	Page<Mensaje> findAll(Pageable pageable);

	
}
