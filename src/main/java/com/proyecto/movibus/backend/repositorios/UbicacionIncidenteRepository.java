package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.UbicacionIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UbicacionIncidenteRepository extends JpaRepository<UbicacionIncidente, Integer> {

    @Query(value = "SELECT * FROM public.ubicacion_incidente", nativeQuery = true)
    List<UbicacionIncidente> listarTodos();

    @Query(value = "SELECT * FROM public.ubicacion_incidente WHERE codigo = :codigo", nativeQuery = true)
    Optional<UbicacionIncidente> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.ubicacion_incidente(codigo, 
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
        UPDATE public.ubicacion_incidente
        SET latitud = :latitud, longitud = :longitud
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("latitud") Double latitud,
            @Param("longitud") Double longitud
    );

    @Modifying
    @Query(value = "DELETE FROM public.ubicacion_incidente WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}
