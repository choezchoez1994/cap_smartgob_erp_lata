package com.capacitacion.capacitacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "persona_cargo", schema = "capacitacion")
public class PersonaCargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona") // Especificar nombre exacto de la BD
    private Long id_persona;

    @Column(name = "id_cargo")
    private Long id_cargo;

    @Column(name = "fecha_asignacion")
    private Date fecha_asignacion;

    @Column(name = "fecha_finalizacion")
    private Date fecha_finalizacion;
}
