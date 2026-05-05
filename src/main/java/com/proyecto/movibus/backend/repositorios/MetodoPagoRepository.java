package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

    @Query(value = "SELECT * FROM public.metodo_pago;", nativeQuery = true)
    List<MetodoPago> listar();
}
