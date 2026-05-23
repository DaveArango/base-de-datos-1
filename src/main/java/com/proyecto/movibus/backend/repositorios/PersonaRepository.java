package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Persona;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO public.persona(
            id,
            primer_nombre,
            segundo_nombre,
            primer_apellido,
            segundo_apellido,
            fecha_nacimiento,
            fecha_registro,
            username,
            password,
            correo,
            estado,
            codigo_telefono,
            codigo_tipo_documento
        )
        VALUES(
            :id,
            :primerNombre,
            :segundoNombre,
            :primerApellido,
            :segundoApellido,
            :fechaNacimiento,
            :fechaRegistro,
            :username,
            :password,
            :correo,
            :estado,
            :codigoTelefono,
            :codigoTipoDocumento
        )
        """, nativeQuery = true)
    void insertarPersona(
            @Param("id") Integer id,
            @Param("primerNombre") String primerNombre,
            @Param("segundoNombre") String segundoNombre,
            @Param("primerApellido") String primerApellido,
            @Param("segundoApellido") String segundoApellido,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("fechaRegistro") LocalDate fechaRegistro,
            @Param("username") String username,
            @Param("password") String password,
            @Param("correo") String correo,
            @Param("estado") Integer estado,
            @Param("codigoTelefono") Integer codigoTelefono,
            @Param("codigoTipoDocumento") Integer codigoTipoDocumento
    );

    @Query(value = "SELECT * FROM public.persona", nativeQuery = true)
    List<Persona> listarPersonas();

    @Query(value = "SELECT * FROM public.persona WHERE id = :id", nativeQuery = true)
    Persona buscarPersonaPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE public.persona
        SET
            primer_nombre = :primerNombre,
            segundo_nombre = :segundoNombre,
            primer_apellido = :primerApellido,
            segundo_apellido = :segundoApellido,
            fecha_nacimiento = :fechaNacimiento,
            fecha_registro = :fechaRegistro,
            username = :username,
            password = :password,
            correo = :correo,
            estado = :estado,
            codigo_telefono = :codigoTelefono,
            codigo_tipo_documento = :codigoTipoDocumento
        WHERE id = :id
        """, nativeQuery = true)
    void actualizarPersona(
            @Param("id") Integer id,
            @Param("primerNombre") String primerNombre,
            @Param("segundoNombre") String segundoNombre,
            @Param("primerApellido") String primerApellido,
            @Param("segundoApellido") String segundoApellido,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("fechaRegistro") LocalDate fechaRegistro,
            @Param("username") String username,
            @Param("password") String password,
            @Param("correo") String correo,
            @Param("estado") Integer estado,
            @Param("codigoTelefono") Integer codigoTelefono,
            @Param("codigoTipoDocumento") Integer codigoTipoDocumento
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.persona WHERE id = :id", nativeQuery = true)
    void eliminarPersona(@Param("id") Integer id);
}