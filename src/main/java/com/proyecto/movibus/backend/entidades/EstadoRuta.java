package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estado_ruta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EstadoRuta {
    @Id
    private Integer codigo;

    @Column(name = "estado_ruta")
    private String estado;
}
