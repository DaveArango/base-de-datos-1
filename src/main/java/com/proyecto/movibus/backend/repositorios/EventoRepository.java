package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query(value = "SELECT * FROM public.evento;", nativeQuery = true)
    List<Evento> listar();
}
