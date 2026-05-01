package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Telefono;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {

    @Query(value = "SELECT * FROM public.telefono;", nativeQuery = true)
    List<Telefono> listar();

    @Query(value = "SELECT * FROM public.telefono WHERE codigo = :codigo", nativeQuery = true)
    Optional<Telefono> buscarPorId(@Param("codigo") Integer codigo);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO public.telefono (
            codigo,
            numero
        )
        VALUES (
            :codigo,
            :numero
        )
    """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("numero") String numero
    );

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE public.telefono
        SET numero = :numero
        WHERE codigo = :codigo
    """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("numero") String numero
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.telefono WHERE codigo = :codigo", nativeQuery = true)
    void eliminar(@Param("codigo") Integer codigo);
}