package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

    @Query(value = "SELECT * FROM public.incidente", nativeQuery = true)
    List<Incidente> listarTodos();

    @Query(value = "SELECT * FROM public.incidente WHERE codigo = :codigo", nativeQuery = true)
    Optional<Incidente> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.incidente(
            codigo, 
            fecha, 
            codigo_estado_incidente,
            codigo_tipo_evento, 
            codigo_ubicacion_incidente, 
            descripcion)
        VALUES(
            :codigo, 
            :fecha, 
            :codigoEstadoIncidente,
            :codigoTipoEvento, 
            :codigoUbicacionIncidente, 
            :descripcion)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("fecha") LocalDate fecha,
            @Param("codigoEstadoIncidente") Integer codigoEstadoIncidente,
            @Param("codigoTipoEvento") Integer codigoTipoEvento,
            @Param("codigoUbicacionIncidente") Integer codigoUbicacionIncidente,
            @Param("descripcion") String descripcion
    );

    @Modifying
    @Query(value = """
        UPDATE public.incidente
        SET fecha = :fecha,
            codigo_estado_incidente = :codigoEstadoIncidente,
            codigo_tipo_evento = :codigoTipoEvento,
            codigo_ubicacion_incidente = :codigoUbicacionIncidente,
            descripcion = :descripcion
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("fecha") LocalDate fecha,
            @Param("codigoEstadoIncidente") Integer codigoEstadoIncidente,
            @Param("codigoTipoEvento") Integer codigoTipoEvento,
            @Param("codigoUbicacionIncidente") Integer codigoUbicacionIncidente,
            @Param("descripcion") String descripcion
    );

    @Modifying
    @Query(value = "DELETE FROM public.incidente WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}