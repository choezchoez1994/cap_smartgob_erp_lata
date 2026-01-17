package com.capacitacion.capacitacion.controller;

import com.capacitacion.capacitacion.dto.CargoDto;
import com.capacitacion.capacitacion.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargo-api")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @PostMapping("/guardar")
    public ResponseEntity<CargoDto> guardarCargo(@RequestBody CargoDto cargoDto) {
        return new ResponseEntity<>(cargoService.guardarCargo(cargoDto), HttpStatus.OK);
    }
}
