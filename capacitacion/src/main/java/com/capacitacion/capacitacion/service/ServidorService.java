package com.capacitacion.capacitacion.service;

import com.capacitacion.capacitacion.PersonaRepository;
import com.capacitacion.capacitacion.ServidorRepository;
import com.capacitacion.capacitacion.dto.PersonaDto;
import com.capacitacion.capacitacion.dto.PersonaServidorDto;
import com.capacitacion.capacitacion.dto.ServidorDto;
import com.capacitacion.capacitacion.entity.Persona;
import com.capacitacion.capacitacion.entity.Servidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServidorService {

    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_INACTIVO = "INACTIVO";

    @Autowired
    private ServidorRepository servidorRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    public ServidorDto guardar(ServidorDto servidorDto) {
        Persona persona = null;

        // Validar si la persona existe por cédula
        if (servidorDto.getPersona() != null && servidorDto.getPersona().getIdentificacion() != null) {
            persona = personaRepository.findPersonaByIdentificacion(servidorDto.getPersona().getIdentificacion());
        }

        // Si la persona NO existe, crear persona y servidor
        if (persona == null) {
            // Crear la persona primero
            PersonaDto personaDto = servidorDto.getPersona();
            if (personaDto == null) {
                throw new RuntimeException("La información de la persona es requerida");
            }
            personaDto = personaService.guardar(personaDto);
            // Buscar la persona recién creada
            persona = personaRepository.findById(personaDto.getId()).orElse(null);
        }

        // Crear el servidor
        Servidor servidor = mapearServidor(servidorDto);
        servidor.setPersona(persona);
        servidor.setEstado(ESTADO_ACTIVO);
        servidor = servidorRepository.save(servidor);

        return mapearServidorDto(servidor);
    }

    public String eliminar(Long id) {
        Servidor servidor = servidorRepository.findById(id).orElse(null);
        if (servidor == null) {
            throw new RuntimeException("Servidor no encontrado");
        }
        servidor.setEstado(ESTADO_INACTIVO);
        servidorRepository.save(servidor);
        return "Servidor eliminado con Exito";
    }

    public PersonaServidorDto activarPersona(String cedula) {
        // Buscar la persona por cédula
        Persona persona = personaRepository.findPersonaByIdentificacion(cedula);
        if (persona == null) {
            throw new RuntimeException("Persona no encontrada");
        }

        // Buscar el servidor relacionado con esta persona
        Servidor servidor = servidorRepository.findFirstByPersona(persona)
                .orElseThrow(() -> new RuntimeException("No se encontró servidor para esta persona"));

        // Si está INACTIVO, cambiar a ACTIVO
        if (ESTADO_INACTIVO.equals(servidor.getEstado())) {
            servidor.setEstado(ESTADO_ACTIVO);
            servidorRepository.save(servidor);
        }

        // Devolver cedula, nombre y apellido
        return PersonaServidorDto.builder()
                .cedula(persona.getIdentificacion())
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .build();
    }

    public List<ServidorDto> obtenerListado() {
        List<Servidor> servidorList = servidorRepository.findServidorByEstado(ESTADO_ACTIVO);
        List<ServidorDto> servidorDtoList = new ArrayList<>(servidorList.size());
        for (Servidor servidor : servidorList) {
            ServidorDto servidorDto = mapearServidorDto(servidor);
            servidorDtoList.add(servidorDto);
        }
        return servidorDtoList;
    }

    private Servidor mapearServidor(ServidorDto servidorDto) {
        return Servidor.builder()
                .fechaIngreso(servidorDto.getFechaIngreso())
                .fechaSalida(servidorDto.getFechaSalida())
                .correoInstitucional(servidorDto.getCorreoInstitucional())
                .estado(servidorDto.getEstado())
                .build();
    }

    private ServidorDto mapearServidorDto(Servidor servidor) {
        ServidorDto servidorDto = ServidorDto.builder()
                .id(servidor.getId())
                .personaId(servidor.getPersona().getId())
                .fechaIngreso(servidor.getFechaIngreso())
                .fechaSalida(servidor.getFechaSalida())
                .correoInstitucional(servidor.getCorreoInstitucional())
                .estado(servidor.getEstado())
                .build();

        // Mapear la persona si está cargada
        if (servidor.getPersona() != null) {
            Persona persona = servidor.getPersona();
            PersonaDto personaDto = PersonaDto.builder()
                    .id(persona.getId())
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
            servidorDto.setPersona(personaDto);
        }

        return servidorDto;
    }

}