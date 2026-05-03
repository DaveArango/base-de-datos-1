package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    @Query(value = "SELECT * FROM public.vehiculo",
            nativeQuery = true)
    List<Vehiculo> listarTodos();

    @Query(value = "SELECT * FROM public.vehiculo WHERE codigo = :codigo",
            nativeQuery = true)
    Optional<Vehiculo> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.vehiculo(
            codigo, 
            modelo, 
            placa, 
            capacidad,
            codigo_estado_vehiculo, 
            codigo_marca,
            codigo_tipo_combustible, 
            codigo_ubicacion_vehiculo)
        VALUES(
            :codigo, 
            :modelo, 
            :placa, 
            :capacidad,
            :codigoEstadoVehiculo, 
            :codigoMarca,
            :codigoTipoCombustible, 
            :codigoUbicacionVehiculo)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("modelo") Integer modelo,
            @Param("placa") String placa,
            @Param("capacidad") Integer capacidad,
            @Param("codigoEstadoVehiculo") Integer codigoEstadoVehiculo,
            @Param("codigoMarca") Integer codigoMarca,
            @Param("codigoTipoCombustible") Integer codigoTipoCombustible,
            @Param("codigoUbicacionVehiculo") Integer codigoUbicacionVehiculo
    );

    @Modifying
    @Query(value = """
        UPDATE public.vehiculo
        SET modelo = :modelo,
            placa = :placa,
            capacidad = :capacidad,
            codigo_estado_vehiculo = :codigoEstadoVehiculo,
            codigo_marca = :codigoMarca,
            codigo_tipo_combustible = :codigoTipoCombustible,
            codigo_ubicacion_vehiculo = :codigoUbicacionVehiculo
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("modelo") Integer modelo,
            @Param("placa") String placa,
            @Param("capacidad") Integer capacidad,
            @Param("codigoEstadoVehiculo") Integer codigoEstadoVehiculo,
            @Param("codigoMarca") Integer codigoMarca,
            @Param("codigoTipoCombustible") Integer codigoTipoCombustible,
            @Param("codigoUbicacionVehiculo") Integer codigoUbicacionVehiculo
    );

    @Modifying
    @Query(value = "DELETE FROM public.vehiculo WHERE codigo = :codigo",
            nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}