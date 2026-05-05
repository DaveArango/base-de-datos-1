package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.UbicacionRecorrido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UbicacionRecorridoRepository extends JpaRepository<UbicacionRecorrido, Integer> {

    @Query(value = "SELECT * FROM public.ubicacion_recorrido", nativeQuery = true)
    List<UbicacionRecorrido> listarTodos();

    @Query(value = "SELECT * FROM public.ubicacion_recorrido WHERE id = :id", nativeQuery = true)
    Optional<UbicacionRecorrido> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.ubicacion_recorrido(id, 
                latitud, 
                longitud)
        VALUES(:id, 
                :latitud, 
                :longitud)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("latitud") Double latitud,
            @Param("longitud") Double longitud
    );

    @Modifying
    @Query(value = """
        UPDATE public.ubicacion_recorrido
        SET latitud = :latitud, longitud = :longitud
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("latitud") Double latitud,
            @Param("longitud") Double longitud
    );

    @Modifying
    @Query(value = "DELETE FROM public.ubicacion_recorrido WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}
