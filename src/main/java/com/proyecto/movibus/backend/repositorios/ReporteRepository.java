package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    @Query(value = "SELECT * FROM public.reporte", nativeQuery = true)
    List<Reporte> listarTodos();

    @Query(value = "SELECT * FROM public.reporte WHERE id = :id", nativeQuery = true)
    Optional<Reporte> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.reporte(id, 
                fecha_generacion, 
                codigo_formato, 
                codigo_tipo_reporte)
        VALUES(:id, 
                :fechaGeneracion, 
                :codigoFormato, 
                :codigoTipoReporte)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("fechaGeneracion") LocalDate fechaGeneracion,
            @Param("codigoFormato") Integer codigoFormato,
            @Param("codigoTipoReporte") Integer codigoTipoReporte
    );

    @Modifying
    @Query(value = """
        UPDATE public.reporte
        SET fecha_generacion = :fechaGeneracion,
            codigo_formato = :codigoFormato,
            codigo_tipo_reporte = :codigoTipoReporte
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("fechaGeneracion") LocalDate fechaGeneracion,
            @Param("codigoFormato") Integer codigoFormato,
            @Param("codigoTipoReporte") Integer codigoTipoReporte
    );

    @Modifying
    @Query(value = "DELETE FROM public.reporte WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}