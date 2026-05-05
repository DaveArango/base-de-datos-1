package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Combustible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombustibleRepository extends JpaRepository<Combustible, Integer> {

    @Query(value = "SELECT * FROM public.combustible;", nativeQuery = true)
    List<Combustible> listar();
}
