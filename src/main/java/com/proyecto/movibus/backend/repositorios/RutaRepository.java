package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {

    @Query(value = "SELECT * FROM public.ruta", nativeQuery = true)
    List<Ruta> listarTodos();

    @Query(value = "SELECT * FROM public.ruta WHERE codigo = :codigo", nativeQuery = true)
    Optional<Ruta> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.ruta(
            codigo, 
            tarifa, 
            nombre, 
            codigo_estado_ruta,
            codigo_inicia_en, 
            codigo_finaliza_en, 
            codigo_recorrido)
        VALUES(
            :codigo, 
            :tarifa, 
            :nombre, 
            :codigoEstadoRuta,
            :codigoIniciaEn, 
            :codigoFinalizaEn, 
            :codigoRecorrido)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("tarifa") Double tarifa,
            @Param("nombre") String nombre,
            @Param("codigoEstadoRuta") Integer codigoEstadoRuta,
            @Param("codigoIniciaEn") Integer codigoIniciaEn,
            @Param("codigoFinalizaEn") Integer codigoFinalizaEn,
            @Param("codigoRecorrido") Integer codigoRecorrido
    );

    @Modifying
    @Query(value = """
        UPDATE public.ruta
        SET tarifa = :tarifa,
            nombre = :nombre,
            codigo_estado_ruta = :codigoEstadoRuta,
            codigo_inicia_en = :codigoIniciaEn,
            codigo_finaliza_en = :codigoFinalizaEn,
            codigo_recorrido = :codigoRecorrido
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("tarifa") Double tarifa,
            @Param("nombre") String nombre,
            @Param("codigoEstadoRuta") Integer codigoEstadoRuta,
            @Param("codigoIniciaEn") Integer codigoIniciaEn,
            @Param("codigoFinalizaEn") Integer codigoFinalizaEn,
            @Param("codigoRecorrido") Integer codigoRecorrido
    );

    @Modifying
    @Query(value = "DELETE FROM public.ruta WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}