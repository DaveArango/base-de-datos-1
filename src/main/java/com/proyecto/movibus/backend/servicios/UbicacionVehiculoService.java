package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.UbicacionVehiculo;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionVehiculoDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionVehiculoService {

    @Autowired
    private UbicacionVehiculoRepository ubicacionVehiculoRepository;

    @Transactional(readOnly = true)
    public List<UbicacionVehiculo> listarUbicaciones() {
        return ubicacionVehiculoRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public UbicacionVehiculo buscarUbicacion(Integer codigo) {
        return ubicacionVehiculoRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearUbicacion(UbicacionVehiculoDTO dto) {
        ubicacionVehiculoRepository.insertar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void actualizarUbicacion(UbicacionVehiculoDTO dto) {
        ubicacionVehiculoRepository.actualizar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void eliminarUbicacion(Integer codigo) {
        ubicacionVehiculoRepository.eliminarPorCodigo(codigo);
    }
}
