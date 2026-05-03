package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Factura;
import com.proyecto.movibus.backend.entidades.dtos.FacturaDTO;
import com.proyecto.movibus.backend.repositorios.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional(readOnly = true)
    public List<Factura> listarFacturas() {
        return facturaRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Factura buscarFactura(Integer codigo) {
        return facturaRepository.buscarPorCodigo(codigo).orElse(null);
    }

    @Transactional
    public void crearFactura(FacturaDTO dto) {
        facturaRepository.insertar(
                dto.getCodigo(),
                dto.getValor(),
                dto.getDescripcion(),
                dto.getFecha(),
                dto.getIdPasajero(),
                dto.getIdViaje()
        );
    }

    @Transactional
    public void actualizarFactura(FacturaDTO dto) {
        facturaRepository.actualizar(
                dto.getCodigo(),
                dto.getValor(),
                dto.getDescripcion(),
                dto.getFecha(),
                dto.getIdPasajero(),
                dto.getIdViaje()
        );
    }

    @Transactional
    public void eliminarFactura(Integer codigo) {
        facturaRepository.eliminarPorCodigo(codigo);
    }
}
