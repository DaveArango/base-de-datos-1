package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query(value = "SELECT * FROM public.marca;", nativeQuery = true)
    List<Marca> listar();
}
