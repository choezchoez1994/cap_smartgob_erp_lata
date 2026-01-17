package com.capacitacion.capacitacion.controller;

import com.capacitacion.capacitacion.dto.PersonaServidorDto;
import com.capacitacion.capacitacion.dto.ServidorDto;
import com.capacitacion.capacitacion.service.ServidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servidor-api")
public class ServidorApi {

    @Autowired
    private ServidorService servidorService;

    @PostMapping("/guardar")
    public ResponseEntity<ServidorDto> guardarServidor(@RequestBody ServidorDto servidorDto) {
        return new ResponseEntity<>(servidorService.guardar(servidorDto), HttpStatus.OK);
    }

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(servidorService.eliminar(id), HttpStatus.OK);
    }

    @GetMapping("/activar-persona/{cedula}")
    public ResponseEntity<PersonaServidorDto> activarPersona(@PathVariable String cedula) {
        return new ResponseEntity<>(servidorService.activarPersona(cedula), HttpStatus.OK);
    }

    @GetMapping("/listado")
    public ResponseEntity<List<ServidorDto>> obtenerListado() {
        return new ResponseEntity<>(servidorService.obtenerListado(), HttpStatus.OK);
    }

}