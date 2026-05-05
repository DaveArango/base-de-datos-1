package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.UbicacionIncidente;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionIncidenteDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionIncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionIncidenteService {

    @Autowired
    private UbicacionIncidenteRepository ubicacionIncidenteRepository;

    @Transactional(readOnly = true)
    public List<UbicacionIncidente> listarUbicaciones() {
        return ubicacionIncidenteRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public UbicacionIncidente buscarUbicacion(Integer codigo) {
        return ubicacionIncidenteRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearUbicacion(UbicacionIncidenteDTO dto) {
        ubicacionIncidenteRepository.insertar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void actualizarUbicacion(UbicacionIncidenteDTO dto) {
        ubicacionIncidenteRepository.actualizar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void eliminarUbicacion(Integer codigo) {
        ubicacionIncidenteRepository.eliminarPorCodigo(codigo);
    }
}
