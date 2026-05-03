package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Integer> {

    @Query(value = "SELECT * FROM public.parada", nativeQuery = true)
    List<Parada> listarTodos();

    @Query(value = "SELECT * FROM public.parada WHERE codigo = :codigo", nativeQuery = true)
    Optional<Parada> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.parada(codigo, 
                nombre, 
                direccion, 
                codigo_ubicacion_parada)
        VALUES(:codigo, 
                :nombre, 
                :direccion, 
                :codigoUbicacionParada)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("nombre") String nombre,
            @Param("direccion") String direccion,
            @Param("codigoUbicacionParada") Integer codigoUbicacionParada
    );

    @Modifying
    @Query(value = """
        UPDATE public.parada
        SET nombre = :nombre,
            direccion = :direccion,
            codigo_ubicacion_parada = :codigoUbicacionParada
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("nombre") String nombre,
            @Param("direccion") String direccion,
            @Param("codigoUbicacionParada") Integer codigoUbicacionParada
    );

    @Modifying
    @Query(value = "DELETE FROM public.parada WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}