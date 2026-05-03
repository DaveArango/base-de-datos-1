package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query(value = "SELECT * FROM public.factura", nativeQuery = true)
    List<Factura> listarTodos();

    @Query(value = "SELECT * FROM public.factura WHERE codigo = :codigo", nativeQuery = true)
    Optional<Factura> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.factura(codigo, 
                valor, 
                descripcion, 
                fecha, 
                id_pasajero, 
                id_viaje)
        VALUES(:codigo, 
                :valor, 
                :descripcion, 
                :fecha, 
                :idPasajero, 
                :idViaje)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("valor") Integer valor,
            @Param("descripcion") String descripcion,
            @Param("fecha") LocalDate fecha,
            @Param("idPasajero") Integer idPasajero,
            @Param("idViaje") Integer idViaje
    );

    @Modifying
    @Query(value = """
        UPDATE public.factura
        SET valor = :valor,
            descripcion = :descripcion,
            fecha = :fecha,
            id_pasajero = :idPasajero,
            id_viaje = :idViaje
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("valor") Integer valor,
            @Param("descripcion") String descripcion,
            @Param("fecha") LocalDate fecha,
            @Param("idPasajero") Integer idPasajero,
            @Param("idViaje") Integer idViaje
    );

    @Modifying
    @Query(value = "DELETE FROM public.factura WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}
