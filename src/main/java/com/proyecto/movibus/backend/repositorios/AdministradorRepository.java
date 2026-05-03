package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Query(value = "SELECT * FROM public.administrador", nativeQuery = true)
    List<Administrador> listarTodos();

    @Query(value = "SELECT * FROM public.administrador WHERE id = :id", nativeQuery = true)
    Optional<Administrador> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.administrador(id, 
                cargo, 
                codigo_nivel_permiso, 
                codigo_administrador)
        VALUES(:id, 
                :cargo, 
                :codigoNivelPermiso, 
                :codigoAdministrador)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("cargo") String cargo,
            @Param("codigoNivelPermiso") Integer codigoNivelPermiso,
            @Param("codigoAdministrador") Integer codigoAdministrador
    );

    @Modifying
    @Query(value = """
        UPDATE public.administrador
        SET cargo = :cargo,
            codigo_nivel_permiso = :codigoNivelPermiso,
            codigo_administrador = :codigoAdministrador
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("cargo") String cargo,
            @Param("codigoNivelPermiso") Integer codigoNivelPermiso,
            @Param("codigoAdministrador") Integer codigoAdministrador
    );

    @Modifying
    @Query(value = "DELETE FROM public.administrador WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}
