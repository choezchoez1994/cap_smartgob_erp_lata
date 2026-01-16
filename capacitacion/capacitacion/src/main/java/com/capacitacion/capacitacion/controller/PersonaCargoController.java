package com.capacitacion.capacitacion.controller;

import com.capacitacion.capacitacion.dto.PersonaCargoDto;
import com.capacitacion.capacitacion.service.PersonaCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/persona-cargo")
public class PersonaCargoController {

    @Autowired
    private PersonaCargoService personaCargoService;

    @PostMapping("/asignar")
    public ResponseEntity<?> asignar(@RequestBody PersonaCargoDto dto) {
        PersonaCargoDto resultado = personaCargoService.asignarCargo(dto);
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El cargo ya está ocupado o no existe.");
        }
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }


    @PostMapping("/liberar")
    public ResponseEntity<String> liberar(@RequestBody PersonaCargoDto dto) {
        // Usamos los campos id_persona y fecha_finalizacion que vienen en el JSON
        String mensaje = personaCargoService.liberarCargo(dto.getId_persona(), dto.getFecha_finalizacion());

        if (mensaje.contains("No se encontró")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        return ResponseEntity.ok(mensaje);
    }
}