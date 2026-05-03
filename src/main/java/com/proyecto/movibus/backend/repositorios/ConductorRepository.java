package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    @Query(value = "SELECT * FROM public.conductor", nativeQuery = true)
    List<Conductor> listarTodos();

    @Query(value = "SELECT * FROM public.conductor WHERE id = :id", nativeQuery = true)
    Optional<Conductor> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.conductor(id, 
                experiencia, 
                codigo_vehiculo)
        VALUES(:id, 
                :experiencia, 
                :codigoVehiculo)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("experiencia") Integer experiencia,
            @Param("codigoVehiculo") Integer codigoVehiculo
    );

    @Modifying
    @Query(value = """
        UPDATE public.conductor
        SET experiencia = :experiencia,
            codigo_vehiculo = :codigoVehiculo
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("experiencia") Integer experiencia,
            @Param("codigoVehiculo") Integer codigoVehiculo
    );

    @Modifying
    @Query(value = "DELETE FROM public.conductor WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}
