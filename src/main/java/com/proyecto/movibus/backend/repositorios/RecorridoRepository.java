package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Recorrido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecorridoRepository extends JpaRepository<Recorrido, Integer> {

    @Query(value = "SELECT * FROM public.recorrido", nativeQuery = true)
    List<Recorrido> listarTodos();

    @Query(value = "SELECT * FROM public.recorrido WHERE id = :id", nativeQuery = true)
    Optional<Recorrido> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.recorrido(id, 
                fecha_inicio, 
                fecha_fin, 
                codigo_estado_recorrido, 
                id_ubicacion_recorrido)
        VALUES(:id, 
                :fechaInicio, 
                :fechaFin, 
                :codigoEstadoRecorrido, 
                :idUbicacionRecorrido)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("codigoEstadoRecorrido") Integer codigoEstadoRecorrido,
            @Param("idUbicacionRecorrido") Integer idUbicacionRecorrido
    );

    @Modifying
    @Query(value = """
        UPDATE public.recorrido
        SET fecha_inicio = :fechaInicio,
            fecha_fin = :fechaFin,
            codigo_estado_recorrido = :codigoEstadoRecorrido,
            id_ubicacion_recorrido = :idUbicacionRecorrido
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("codigoEstadoRecorrido") Integer codigoEstadoRecorrido,
            @Param("idUbicacionRecorrido") Integer idUbicacionRecorrido
    );

    @Modifying
    @Query(value = "DELETE FROM public.recorrido WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}
