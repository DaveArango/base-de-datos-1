package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.EstadoViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoViajeRepository extends JpaRepository<EstadoViaje, Integer> {

    @Query(value = "SELECT * FROM public.estado_viaje;", nativeQuery = true)
    List<EstadoViaje> listar();
}
