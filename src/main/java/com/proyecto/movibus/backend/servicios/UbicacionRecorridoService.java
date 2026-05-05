package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.UbicacionRecorrido;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionRecorridoDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionRecorridoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionRecorridoService {

    @Autowired
    private UbicacionRecorridoRepository ubicacionRecorridoRepository;

    @Transactional(readOnly = true)
    public List<UbicacionRecorrido> listarUbicaciones() {
        return ubicacionRecorridoRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public UbicacionRecorrido buscarUbicacion(Integer id) {
        return ubicacionRecorridoRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearUbicacion(UbicacionRecorridoDTO dto) {
        ubicacionRecorridoRepository.insertar(dto.getId(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void actualizarUbicacion(UbicacionRecorridoDTO dto) {
        ubicacionRecorridoRepository.actualizar(dto.getId(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void eliminarUbicacion(Integer id) {
        ubicacionRecorridoRepository.eliminarPorId(id);
    }
}
