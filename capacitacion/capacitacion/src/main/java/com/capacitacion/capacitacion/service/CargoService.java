package com.capacitacion.capacitacion.service;

import com.capacitacion.capacitacion.CargoRepository;
import com.capacitacion.capacitacion.dto.CargoDto;
import com.capacitacion.capacitacion.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    //Definimos los estados de asignacion (true) o (dalse)
    private static final Boolean ASIGNACION_ACTIVA = true;
    private static final Boolean ASIGNACION_INACTIVA = false;

    @Autowired
    private CargoRepository  cargoRepository;

//Guardar Cargo
    public CargoDto guardarCargo(CargoDto cargoDto) {
        Cargo cargo = mapearCargo(cargoDto);
        cargo = cargoRepository.save(cargo);
        cargoDto = mapearCargoDto(cargo);
        return cargoDto;
    }


private Cargo mapearCargo(CargoDto cargoDto) {
    return Cargo.builder()
            .nombre(cargoDto.getNombre())
            .salario(cargoDto.getSalario())
            .codigo(cargoDto.getCodigo())
            .asignado(ASIGNACION_INACTIVA) //Por defecto, la asignacion es false
            .build();


}

    private CargoDto mapearCargoDto(Cargo cargo) {
        return CargoDto.builder()
                .nombre(cargo.getNombre())
                .salario(cargo.getSalario())
                .codigo(cargo.getCodigo())
                .asignado(cargo.getAsignado())
                .build();


    }

}
