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

public class PersonaCargoDto {
    private Long id;
    private Long id_persona;
    private Long id_cargo;
    private Date fecha_asignacion;
    private Date fecha_finalizacion;

}
