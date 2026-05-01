package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidente")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Incidente {
    @Id
    private Integer codigo;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "codigo_estado_incidente", nullable = false)
    private EstadoIncidente estadoIncidente;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_evento", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "codigo_ubicacion_incidente", nullable = false)
    private UbicacionIncidente ubicacionIncidente;
}
