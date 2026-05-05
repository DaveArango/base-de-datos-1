package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.NivelPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NivelPermisoRepository extends JpaRepository<NivelPermiso, Integer> {

    @Query(value = "SELECT * FROM public.nivel_permiso;", nativeQuery = true)
    List<NivelPermiso> listar();
}
