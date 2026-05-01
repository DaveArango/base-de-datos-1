package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Viaje {
    @Id
    private Integer id;

    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @Column(name = "valor_pagado")
    private BigDecimal valor_pagado;

    @ManyToOne
    @JoinColumn(name = "codigo_estado_viaje", nullable = false)
    private EstadoViaje estadoViaje;

    @ManyToOne
    @JoinColumn(name = "codigo_pasajero", nullable = false)
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "codigo_recorrido", nullable = false)
    private Recorrido recorrido;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;
}
