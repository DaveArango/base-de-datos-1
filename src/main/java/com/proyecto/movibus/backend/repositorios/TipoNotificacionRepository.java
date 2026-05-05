package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Integer> {

    @Query(value = "SELECT * FROM public.tipo_notificacion;", nativeQuery = true)
    List<TipoNotificacion> listar();
}
