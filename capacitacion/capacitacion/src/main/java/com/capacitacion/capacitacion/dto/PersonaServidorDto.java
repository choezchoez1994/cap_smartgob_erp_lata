package com.capacitacion.capacitacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaServidorDto {

    private String cedula;
    private String nombre;
    private String apellido;

}