package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    @Query(value = "SELECT * FROM public.notificacion", nativeQuery = true)
    List<Notificacion> listarTodos();

    @Query(value = "SELECT * FROM public.notificacion WHERE codigo = :codigo", nativeQuery = true)
    Optional<Notificacion> buscarPorCodigo(@Param("codigo") Integer codigo);

    @Modifying
    @Query(value = """
        INSERT INTO public.notificacion(codigo, 
                descripcion, 
                titulo, 
                id_tipo_notificacion)
        VALUES(:codigo, 
                :descripcion, 
                :titulo, 
                :idTipoNotificacion)
        """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("descripcion") String descripcion,
            @Param("titulo") String titulo,
            @Param("idTipoNotificacion") Integer idTipoNotificacion
    );

    @Modifying
    @Query(value = """
        UPDATE public.notificacion
        SET descripcion = :descripcion,
            titulo = :titulo,
            id_tipo_notificacion = :idTipoNotificacion
        WHERE codigo = :codigo
        """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("descripcion") String descripcion,
            @Param("titulo") String titulo,
            @Param("idTipoNotificacion") Integer idTipoNotificacion
    );

    @Modifying
    @Query(value = "DELETE FROM public.notificacion WHERE codigo = :codigo", nativeQuery = true)
    void eliminarPorCodigo(@Param("codigo") Integer codigo);
}
