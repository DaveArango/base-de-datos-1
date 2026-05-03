package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Persona;
import com.proyecto.movibus.backend.entidades.dtos.PersonaDTO;
import com.proyecto.movibus.backend.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return personaRepository.listarPersonas();
    }

    @Transactional(readOnly = true)
    public Persona buscarPersona(Integer id) {
        return personaRepository.buscarPersonaPorId(id);
    }

    @Transactional
    public void crearPersona(PersonaDTO dto) {
        personaRepository.insertarPersona(
                dto.getId(),
                dto.getPrimerNombre(),
                dto.getSegundoNombre(),
                dto.getPrimerApellido(),
                dto.getSegundoApellido(),
                dto.getFechaNacimiento(),
                dto.getFechaRegistro(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getCorreo(),
                dto.getEstado(),
                dto.getCodigoTelefono(),
                dto.getCodigoDocumento()
        );
    }

    @Transactional
    public void actualizarPersona(PersonaDTO dto) {
        personaRepository.actualizarPersona(
                dto.getId(),
                dto.getPrimerNombre(),
                dto.getSegundoNombre(),
                dto.getPrimerApellido(),
                dto.getSegundoApellido(),
                dto.getFechaNacimiento(),
                dto.getFechaRegistro(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getCorreo(),
                dto.getEstado(),
                dto.getCodigoTelefono(),
                dto.getCodigoDocumento()
        );
    }

    @Transactional
    public void eliminarPersona(Integer id) {
        personaRepository.eliminarPersona(id);
    }
}