package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {

    @Query(
            value = """
            SELECT p.*
            FROM public.pasajero p
            """,
            nativeQuery = true
    )
    List<Pasajero> listarTodos();

    @Query(
            value = """
            SELECT p.*
            FROM public.pasajero p
            WHERE p.id = :id
            """,
            nativeQuery = true
    )
    Optional<Pasajero> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(
            value = """
            INSERT INTO public.pasajero (id, 
                    codigo_metodo_pago, 
                    codigo_tipo_tarifa)
            VALUES (:id, 
                    :codigoMetodoPago, 
                    :codigoTarifa)
            """,
            nativeQuery = true
    )
    void insertar(
            @Param("id") Integer id,
            @Param("codigoMetodoPago") Integer codigoMetodoPago,
            @Param("codigoTarifa") Integer codigoTarifa
    );

    @Modifying
    @Query(
            value = """
            UPDATE public.pasajero
            SET codigo_metodo_pago = :codigoMetodoPago,
                codigo_tipo_tarifa = :codigoTarifa
            WHERE id = :id
            """,
            nativeQuery = true
    )
    void actualizar(
            @Param("id") Integer id,
            @Param("codigoMetodoPago") Integer codigoMetodoPago,
            @Param("codigoTarifa") Integer codigoTarifa
    );

    @Modifying
    @Query(
            value = "DELETE FROM pasajero WHERE id = :id",
            nativeQuery = true
    )
    void eliminarPorId(@Param("id") Integer id);
}
