package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reporte {
    @Id
    private Integer id;

    @Column(name = "fecha_generacion")
    private LocalDate fecha_generacion;

    @ManyToOne
    @JoinColumn(name = "codigo_formato", nullable = false)
    private Formato formato;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_reporte", nullable = false)
    private TipoReporte tipoReporte;
}
