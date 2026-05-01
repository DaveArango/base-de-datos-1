package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Persona;
import com.proyecto.movibus.backend.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> listar() {
        return personaRepository.listar();
    }

    public Persona buscarPorId(Integer id) {
        return personaRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    public void insertar(
            Integer id,
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido,
            LocalDate fechaNacimiento,
            LocalDate fechaRegistro,
            String username,
            String password,
            String correo,
            Integer estado,
            Integer codigoTelefono,
            Integer codigoTipoDocumento
    ) {
        personaRepository.insertar(
                id,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                fechaNacimiento,
                fechaRegistro,
                username,
                password,
                correo,
                estado,
                codigoTelefono,
                codigoTipoDocumento
        );
    }

    public void actualizar(
            Integer id,
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido,
            LocalDate fechaNacimiento,
            LocalDate fechaRegistro,
            String username,
            String password,
            String correo,
            Integer estado,
            Integer codigoTelefono,
            Integer codigoTipoDocumento
    ) {
        personaRepository.actualizar(
                id,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                fechaNacimiento,
                fechaRegistro,
                username,
                password,
                correo,
                estado,
                codigoTelefono,
                codigoTipoDocumento
        );
    }

    public void eliminar(Integer id) {
        personaRepository.eliminar(id);
    }
}