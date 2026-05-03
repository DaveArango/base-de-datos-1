package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Viaje;
import com.proyecto.movibus.backend.entidades.dtos.ViajeDTO;
import com.proyecto.movibus.backend.repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Transactional(readOnly = true)
    public List<Viaje> listarViajes() {
        return viajeRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Viaje buscarViaje(Integer id) {
        return viajeRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearViaje(ViajeDTO dto) {
        viajeRepository.insertar(
                dto.getId(),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getValorPagado(),
                dto.getCodigoEstadoViaje(),
                dto.getCodigoPasajero(),
                dto.getCodigoRecorrido(),
                dto.getOrigen(),
                dto.getDestino()
        );
    }

    @Transactional
    public void actualizarViaje(ViajeDTO dto) {
        viajeRepository.actualizar(
                dto.getId(),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getValorPagado(),
                dto.getCodigoEstadoViaje(),
                dto.getCodigoPasajero(),
                dto.getCodigoRecorrido(),
                dto.getOrigen(),
                dto.getDestino()
        );
    }

    @Transactional
    public void eliminarViaje(Integer id) {
        viajeRepository.eliminarPorId(id);
    }
}
