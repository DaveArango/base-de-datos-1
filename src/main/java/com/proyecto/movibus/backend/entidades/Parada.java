package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parada")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Parada {
    @Id
    private Integer codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "codigo_ubicacion_parada", nullable = false)
    private UbicacionParada ubicacionParada;
}
