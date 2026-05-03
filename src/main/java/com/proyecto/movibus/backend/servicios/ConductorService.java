package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Conductor;
import com.proyecto.movibus.backend.entidades.dtos.ConductorDTO;
import com.proyecto.movibus.backend.repositorios.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Transactional(readOnly = true)
    public List<Conductor> listarConductores() {
        return conductorRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Conductor buscarConductor(Integer id) {
        return conductorRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearConductor(ConductorDTO dto) {
        conductorRepository.insertar(dto.getId(),
                dto.getExperiencia(),
                dto.getCodigoVehiculo());
    }

    @Transactional
    public void actualizarConductor(ConductorDTO dto) {
        conductorRepository.actualizar(dto.getId(),
                dto.getExperiencia(),
                dto.getCodigoVehiculo());
    }

    @Transactional
    public void eliminarConductor(Integer id) {
        conductorRepository.eliminarPorId(id);
    }
}
