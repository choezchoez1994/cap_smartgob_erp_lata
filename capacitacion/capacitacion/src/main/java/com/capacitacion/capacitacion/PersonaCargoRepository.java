package com.capacitacion.capacitacion;

import com.capacitacion.capacitacion.entity.PersonaCargo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaCargoRepository extends JpaRepository<PersonaCargo, Long> {
    //Se crea una consulta SQL directa para obtener los registros que no tengan fecha de finalizacion aun (Aun este en el cargo)
    @Query("SELECT pc FROM PersonaCargo pc WHERE pc.id_persona = :idPersona AND pc.fecha_finalizacion IS NULL")
    Optional<PersonaCargo> findActiveAssignmentByPersona(@Param("idPersona") Long idPersona);
}
