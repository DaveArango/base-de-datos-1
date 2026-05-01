package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ubicacion_recorrido")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ubicacion_Recorrido {
    @Id
    private Integer id;

    @Column(name = "latitud", nullable = false)
    private BigDecimal latitud;

    @Column(name = "longitud", nullable = false)
    private BigDecimal longitud;
}
