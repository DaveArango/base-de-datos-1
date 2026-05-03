package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Notificacion;
import com.proyecto.movibus.backend.entidades.dtos.NotificacionDTO;
import com.proyecto.movibus.backend.repositorios.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Transactional(readOnly = true)
    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Notificacion buscarNotificacion(Integer codigo) {
        return notificacionRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearNotificacion(NotificacionDTO dto) {
        notificacionRepository.insertar(
                dto.getCodigo(),
                dto.getDescripcion(),
                dto.getTitulo(),
                dto.getIdTipoNotificacion()
        );
    }

    @Transactional
    public void actualizarNotificacion(NotificacionDTO dto) {
        notificacionRepository.actualizar(
                dto.getCodigo(),
                dto.getDescripcion(),
                dto.getTitulo(),
                dto.getIdTipoNotificacion()
        );
    }

    @Transactional
    public void eliminarNotificacion(Integer codigo) {
        notificacionRepository.eliminarPorCodigo(codigo);
    }
}
