package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.EstadoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoIncidenteRepository extends JpaRepository<EstadoIncidente, Integer> {

    @Query(value = "SELECT * FROM public.estado_incidente;", nativeQuery = true)
    List<EstadoIncidente> listar();
}
