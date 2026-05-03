package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private Integer codigo;
    private String titulo;
    private String descripcion; // nullable
    private Integer idTipoNotificacion;
}