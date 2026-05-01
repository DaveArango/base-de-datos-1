package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tipo_reporte")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TipoReporte {
    @Id
    private Integer codigo;

    @Column(name = "tipo_reporte")
    private String tipo;
}
