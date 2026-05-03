package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

    @Query(value = "SELECT * FROM public.viaje", nativeQuery = true)
    List<Viaje> listarTodos();

    @Query(value = "SELECT * FROM public.viaje WHERE id = :id", nativeQuery = true)
    Optional<Viaje> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.viaje(
            id, 
            fecha_inicio, 
            fecha_fin, 
            valor_pagado,
            codigo_estado_viaje, 
            codigo_pasajero,
            codigo_recorrido, 
            origen, 
            destino)
        VALUES(
            :id, 
            :fechaInicio, 
            :fechaFin, 
            :valorPagado,
            :codigoEstadoViaje, 
            :codigoPasajero,
            :codigoRecorrido, 
            :origen, 
            :destino)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("valorPagado") Double valorPagado,
            @Param("codigoEstadoViaje") Integer codigoEstadoViaje,
            @Param("codigoPasajero") Integer codigoPasajero,
            @Param("codigoRecorrido") Integer codigoRecorrido,
            @Param("origen") String origen,
            @Param("destino") String destino
    );

    @Modifying
    @Query(value = """
        UPDATE public.viaje
        SET fecha_inicio = :fechaInicio,
            fecha_fin = :fechaFin,
            valor_pagado = :valorPagado,
            codigo_estado_viaje = :codigoEstadoViaje,
            codigo_pasajero = :codigoPasajero,
            codigo_recorrido = :codigoRecorrido,
            origen = :origen,
            destino = :destino
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("valorPagado") Double valorPagado,
            @Param("codigoEstadoViaje") Integer codigoEstadoViaje,
            @Param("codigoPasajero") Integer codigoPasajero,
            @Param("codigoRecorrido") Integer codigoRecorrido,
            @Param("origen") String origen,
            @Param("destino") String destino
    );

    @Modifying
    @Query(value = "DELETE FROM public.viaje WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}
