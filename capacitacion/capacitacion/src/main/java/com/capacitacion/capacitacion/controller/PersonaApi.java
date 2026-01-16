package com.capacitacion.capacitacion.controller;

import com.capacitacion.capacitacion.dto.PersonaDto;
import com.capacitacion.capacitacion.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona-api")
public class PersonaApi {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/guardar")
    public ResponseEntity<PersonaDto> guardarPersona(@RequestBody PersonaDto personaDto) {
        return new ResponseEntity<>(personaService.guardar(personaDto), HttpStatus.OK);
    }

    @GetMapping("/eliminar/{cedula}")
    public ResponseEntity<String> eliminar(@PathVariable String cedula) {
        return new ResponseEntity<>(personaService.eliminar(cedula), HttpStatus.OK);
    }

    @GetMapping("/obtener-lista/{estado}")
    public List<PersonaDto> obtenerLista(@PathVariable String estado) {
        return personaService.obtenerTodosByEstado(estado);
    }

    @GetMapping("/consulta/{identificacion}")
    public PersonaDto consultarPersona(@PathVariable String identificacion) {
        return personaService.consultarPersona(identificacion);
    }
}
