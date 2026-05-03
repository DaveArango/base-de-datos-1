package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Vehiculo;
import com.proyecto.movibus.backend.entidades.dtos.VehiculoDTO;
import com.proyecto.movibus.backend.repositorios.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Vehiculo buscarVehiculo(Integer codigo) {
        return vehiculoRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearVehiculo(VehiculoDTO dto) {
        vehiculoRepository.insertar(
                dto.getCodigo(),
                dto.getModelo(),
                dto.getPlaca(),
                dto.getCapacidad(),
                dto.getCodigoEstadoVehiculo(),
                dto.getCodigoMarca(),
                dto.getCodigoTipoCombustible(),
                dto.getCodigoUbicacionVehiculo()
        );
    }

    @Transactional
    public void actualizarVehiculo(VehiculoDTO dto) {
        vehiculoRepository.actualizar(
                dto.getCodigo(),
                dto.getModelo(),
                dto.getPlaca(),
                dto.getCapacidad(),
                dto.getCodigoEstadoVehiculo(),
                dto.getCodigoMarca(),
                dto.getCodigoTipoCombustible(),
                dto.getCodigoUbicacionVehiculo()
        );
    }

    @Transactional
    public void eliminarVehiculo(Integer codigo) {
        vehiculoRepository.eliminarPorCodigo(codigo);
    }
}
