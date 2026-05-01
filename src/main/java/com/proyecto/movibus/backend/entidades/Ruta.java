package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ruta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ruta {
    @Id
    private Integer codigo;

    @Column(name = "tarifa")
    private BigDecimal tarifa;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "codigo_estado_ruta", nullable = false)
    private EstadoRuta estadoRuta;

    @ManyToOne
    @JoinColumn(name = "codigo_inicia_en", nullable = false)
    private Parada inicia_en;

    @ManyToOne
    @JoinColumn(name = "codigo_finaliza_en", nullable = false)
    private Parada finaliza_en;

    @ManyToOne
    @JoinColumn(name = "codigo_recorrido", nullable = false)
    private Recorrido recorrido;
}
