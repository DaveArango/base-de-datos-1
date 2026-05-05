package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoReporteRepository extends JpaRepository<TipoReporte, Integer> {

    @Query(value = "SELECT * FROM public.tipo_reporte;", nativeQuery = true)
    List<TipoReporte> listar();
}
