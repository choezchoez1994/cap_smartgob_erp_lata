package com.capacitacion.capacitacion.service;

import com.capacitacion.capacitacion.CargoRepository;
import com.capacitacion.capacitacion.PersonaCargoRepository;
import com.capacitacion.capacitacion.dto.PersonaCargoDto;
import com.capacitacion.capacitacion.entity.Cargo;
import com.capacitacion.capacitacion.entity.PersonaCargo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonaCargoService {


    @Autowired
    private PersonaCargoRepository personaCargoRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Transactional
    public PersonaCargoDto asignarCargo(PersonaCargoDto personaCargoDto) {
        //Primero hacemos una validacion para saber si el cargo fue ocupado
        //Buscamos por el ID
        Cargo cargo = cargoRepository.findById(personaCargoDto.getId_cargo()).orElse(null);

        // Si el cargo ya está ocupado se retorna nullo
        if (cargo == null || cargo.getAsignado()) {
            System.out.println("Cargo no encontrado o no existe");
            return null;
        }

        //Asignamos como ocupado (TRUE) y lo guardamos (porque el cargo esta disponib;e)
        cargo.setAsignado(true);
        cargoRepository.save(cargo);

        //Guardamos en la nueva tabla de persona_cargo
        PersonaCargo personaCargo = mapearPersonaCargo(personaCargoDto);
        personaCargoRepository.save(personaCargo);

        return personaCargoDto;
    }


    public String liberarCargo(Long idPersona, Date fechaFinalizacion) {
        // Buscamos si ese cargo se encuentra activo
        PersonaCargo cargoActivo = personaCargoRepository.findActiveAssignmentByPersona(idPersona).orElse(null);

        // Si no hay asignación, se muestra un mensaje
        if (cargoActivo == null) {
            return "No se encontró una asignación activa para esta persona";
        }

        // Asigno la nueva fecha de finalizacion
        cargoActivo.setFecha_finalizacion(fechaFinalizacion);
        personaCargoRepository.save(cargoActivo);

        // Asigno como false a ese cargo en mi tabla de cargo
        Cargo cargo = cargoRepository.findById(cargoActivo.getId_cargo()).orElse(null);
        if (cargo != null) {
            cargo.setAsignado(false);
            cargoRepository.save(cargo);
        }

        return "Cargo liberado con Exito. Puede asignarse a otro usuario";
    }



    private PersonaCargo mapearPersonaCargo(PersonaCargoDto personaCargoDto) {
        return PersonaCargo.builder()
                .id_persona(personaCargoDto.getId_persona())
                .id_cargo(personaCargoDto.getId_cargo())
                .fecha_asignacion(personaCargoDto.getFecha_asignacion())
                .fecha_finalizacion(personaCargoDto.getFecha_finalizacion())
                .build();


    }

    private PersonaCargoDto mapearPersonaCargoDto(PersonaCargo  personaCargo) {
        return PersonaCargoDto.builder()
                .id_persona(personaCargo.getId_persona())
                .id_cargo(personaCargo.getId_cargo())
                .fecha_asignacion(personaCargo.getFecha_asignacion())
                .fecha_finalizacion(personaCargo.getFecha_finalizacion())
                .build();


    }

}
