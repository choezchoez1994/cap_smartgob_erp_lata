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
public class PersonaDto {

    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String celular;
    private Boolean discapacidad;
    private Integer estadoCivil;
    private String correo;
    private String estado;

}
