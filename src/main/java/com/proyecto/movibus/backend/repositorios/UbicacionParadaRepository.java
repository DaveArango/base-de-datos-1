package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.UbicacionParada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UbicacionParadaRepository extends JpaRepository<UbicacionParada, Integer> {

    @Query(value = "SELECT * FROM public.ubicacion_parada", nativeQuery = true)
    List<UbicacionParada> listarTodos();

    @Query(value = "SELECT * FROM public.ubicacion_parada WHERE codigo = :codigo", nativeQuery = true)
    Optional<UbicacionParada> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.ubicacion_parada(codigo, 
                latitud, 
                longitud)
        VALUES(:codigo, 
                :latitud, 
                :longitud)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("latitud") Double latitud,
            @Param("longitud") Double longitud
    );

    @Modifying
    @Query(value = """
        UPDATE public.ubicacion_parada
        SET latitud = :latitud, longitud = :longitud
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("latitud") Double latitud,
            @Param("longitud") Double longitud
    );

    @Modifying
    @Query(value = "DELETE FROM public.ubicacion_parada WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}
