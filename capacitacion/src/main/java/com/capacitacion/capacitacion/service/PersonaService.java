package com.capacitacion.capacitacion.service;

import com.capacitacion.capacitacion.PersonaRepository;
import com.capacitacion.capacitacion.dto.PersonaDto;
import com.capacitacion.capacitacion.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_INACTIVO = "INACTIVO";

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaDto guardar(PersonaDto personaDto) {
        Persona persona = mapearPersona(personaDto);
        persona = personaRepository.save(persona);
        personaDto = mapearPersonaDto(persona);
        return personaDto;
    }

    public String eliminar(String cedula) {
        Persona persona = personaRepository.findPersonaByIdentificacion(cedula);
        persona.setEstado(ESTADO_INACTIVO);
        persona = personaRepository.save(persona);
        return "Persona eliminada con Exito";
    }

    public List<PersonaDto> obtenerTodosByEstado(String estado) {
        List<Persona> personaList = personaRepository.findPersonaByEstado(estado);
        List<PersonaDto> personaDtoList = new ArrayList<>(personaList.size());
        for (Persona persona : personaList) {
            PersonaDto personaDto = mapearPersonaDto(persona);
            personaDtoList.add(personaDto);
        }
        return personaDtoList;
    }

    public PersonaDto consultarPersona(String identificacion) {
        Persona persona = personaRepository.findPersonaByIdentificacion(identificacion);
        if (persona != null && persona.getEstado().equals(ESTADO_ACTIVO)) {
            return mapearPersonaDto(persona);
        }
        return null;
    }

    private Persona mapearPersona(PersonaDto personaDto) {
        return Persona.builder()
                .identificacion(personaDto.getIdentificacion())
                .nombre(personaDto.getNombre())
                .apellido(personaDto.getApellido())
                .fechaNacimiento(personaDto.getFechaNacimiento())
                .direccion(personaDto.getDireccion())
                .telefono(personaDto.getTelefono())
                .celular(personaDto.getCelular())
                .discapacidad(personaDto.getDiscapacidad())
                .estadoCivil(personaDto.getEstadoCivil())
                .correo(personaDto.getCorreo())
                .estado(ESTADO_ACTIVO)
                .build();
    }

    private PersonaDto mapearPersonaDto(Persona persona) {
        return PersonaDto.builder()
                .identificacion(persona.getIdentificacion())
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .fechaNacimiento(persona.getFechaNacimiento())
                .direccion(persona.getDireccion())
                .telefono(persona.getTelefono())
                .celular(persona.getCelular())
                .discapacidad(persona.getDiscapacidad())
                .estadoCivil(persona.getEstadoCivil())
                .correo(persona.getCorreo())
                .estado(persona.getEstado())
                .build();
    }
}
