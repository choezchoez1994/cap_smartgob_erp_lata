package com.capacitacion.capacitacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServidorDto {

    private Long id;
    private Long personaId;
    private PersonaDto persona;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String correoInstitucional;
    private String estado;

}