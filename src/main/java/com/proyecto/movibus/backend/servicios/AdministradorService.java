package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Administrador;
import com.proyecto.movibus.backend.entidades.dtos.AdministradorDTO;
import com.proyecto.movibus.backend.repositorios.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(readOnly = true)
    public List<Administrador> listarAdministradores() {
        return administradorRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Administrador buscarAdministrador(Integer id) {
        return administradorRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearAdministrador(AdministradorDTO dto) {
        administradorRepository.insertar(
                dto.getId(),
                dto.getCargo(),
                dto.getCodigoNivelPermiso(),
                dto.getCodigoAdministrador()
        );
    }

    @Transactional
    public void actualizarAdministrador(AdministradorDTO dto) {
        administradorRepository.actualizar(
                dto.getId(),
                dto.getCargo(),
                dto.getCodigoNivelPermiso(),
                dto.getCodigoAdministrador()
        );
    }

    @Transactional
    public void eliminarAdministrador(Integer id) {
        administradorRepository.eliminarPorId(id);
    }
}
