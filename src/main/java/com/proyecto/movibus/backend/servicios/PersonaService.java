package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Persona;
import com.proyecto.movibus.backend.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public void crearPersona(Persona persona) {

        personaRepository.insertarPersona(
                persona.getId(),
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getFechaNacimiento(),
                LocalDate.now(),
                persona.getUsername(),
                persona.getPassword(),
                persona.getCorreo(),
                persona.getEstado(),
                persona.getTelefono(),
                persona.getDocumento()
        );
    }

    public List<Persona> listarPersonas() {
        return personaRepository.listarPersonas();
    }

    public Persona buscarPersona(Integer id) {
        return personaRepository.buscarPersonaPorId(id);
    }

    public void actualizarPersona(Persona persona) {
        personaRepository.actualizarPersona(
                persona.getId(),
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getFechaNacimiento(),
                persona.getFechaRegistro(),
                persona.getUsername(),
                persona.getPassword(),
                persona.getCorreo(),
                persona.getEstado(),
                persona.getTelefono(),
                persona.getDocumento()
        );
    }

    public void eliminarPersona(Integer id) {
        personaRepository.eliminarPersona(id);
    }
}