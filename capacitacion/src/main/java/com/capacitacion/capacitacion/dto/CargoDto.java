package com.capacitacion.capacitacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CargoDto {
    private Long idCargo;
    private String nombre;
    private Double salario;
    private String codigo;
    private Boolean asignado;
}
