package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.EstadoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoVehiculoRepository extends JpaRepository<EstadoVehiculo, Integer> {

    @Query(value = "SELECT * FROM public.estado_vehiculo;", nativeQuery = true)
    List<EstadoVehiculo> listar();
}
