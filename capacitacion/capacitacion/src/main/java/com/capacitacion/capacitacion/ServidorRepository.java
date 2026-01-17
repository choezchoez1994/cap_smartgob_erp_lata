package com.capacitacion.capacitacion;

import com.capacitacion.capacitacion.entity.Persona;
import com.capacitacion.capacitacion.entity.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long> {

    List<Servidor> findServidorByEstado(String estado);

    Optional<Servidor> findFirstByPersona(Persona persona);

}