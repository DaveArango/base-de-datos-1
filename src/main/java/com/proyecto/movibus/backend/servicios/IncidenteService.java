package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Incidente;
import com.proyecto.movibus.backend.entidades.dtos.IncidenteDTO;
import com.proyecto.movibus.backend.repositorios.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidenteService {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Transactional(readOnly = true)
    public List<Incidente> listarIncidentes() {
        return incidenteRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Incidente buscarIncidente(Integer codigo) {
        return incidenteRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearIncidente(IncidenteDTO dto) {
        incidenteRepository.insertar(
                dto.getCodigo(),
                dto.getFecha(),
                dto.getCodigoEstadoIncidente(),
                dto.getCodigoTipoEvento(),
                dto.getCodigoUbicacionIncidente(),
                dto.getDescripcion()
        );
    }

    @Transactional
    public void actualizarIncidente(IncidenteDTO dto) {
        incidenteRepository.actualizar(
                dto.getCodigo(),
                dto.getFecha(),
                dto.getCodigoEstadoIncidente(),
                dto.getCodigoTipoEvento(),
                dto.getCodigoUbicacionIncidente(),
                dto.getDescripcion()
        );
    }

    @Transactional
    public void eliminarIncidente(Integer codigo) {
        incidenteRepository.eliminarPorCodigo(codigo);
    }
}
