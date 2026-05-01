package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificacion")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notificacion {
    @Id
    private Integer codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_notificacion", nullable = false)
    private TipoNotificacion tipoNotificacion;
}
