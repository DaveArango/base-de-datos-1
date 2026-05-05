package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.EstadoRecorrido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRecorridoRepository extends JpaRepository<EstadoRecorrido, Integer> {

    @Query(value = "SELECT * FROM public.estado_recorrido;", nativeQuery = true)
    List<EstadoRecorrido> listar();
}
