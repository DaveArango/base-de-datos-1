package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {

    @Query(value = "SELECT * FROM public.tarifa;", nativeQuery = true)
    List<Tarifa> listar();
}
