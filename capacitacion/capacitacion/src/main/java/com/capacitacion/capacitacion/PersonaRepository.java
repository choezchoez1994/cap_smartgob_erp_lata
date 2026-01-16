package com.capacitacion.capacitacion;

import com.capacitacion.capacitacion.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findPersonaByEstado(String estado);

    Persona findPersonaByIdentificacion(String cedula);

}
