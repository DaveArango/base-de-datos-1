package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Documento;
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
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    @Query(value = "SELECT * FROM public.documento;", nativeQuery = true)
    List<Documento> listar();

    @Query(value = "SELECT * FROM public.documento WHERE codigo = :codigo", nativeQuery = true)
    Optional<Documento> buscarPorId(@Param("codigo") Integer codigo);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO public.documento (
            codigo,
            tipo_documento
        )
        VALUES (
            :codigo,
            :tipo_documento
        )
    """, nativeQuery = true)
    void insertar(
            @Param("codigo") Integer codigo,
            @Param("tipo_documento") String tipo_documento
    );

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE public.documento
        SET tipo_documento = :tipo_documento
        WHERE codigo = :codigo
    """, nativeQuery = true)
    void actualizar(
            @Param("codigo") Integer codigo,
            @Param("tipo_documento") String tipo_documento
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.documento WHERE codigo = :codigo", nativeQuery = true)
    void eliminar(@Param("codigo") Integer codigo);
}