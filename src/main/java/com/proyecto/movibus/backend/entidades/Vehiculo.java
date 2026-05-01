package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehiculo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vehiculo {
    @Id
    private Integer codigo;

    @Column(name = "modelo")
    private Integer modelo;

    @Column(name = "placa")
    private String placa;

    @Column(name = "capacidad")
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "codigo_estado_vehiculo", nullable = false)
    private EstadoVehiculo estadoVehiculo;

    @ManyToOne
    @JoinColumn(name = "codigo_marca", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_combustible", nullable = false)
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "codigo_ubicacion_vehiculo", nullable = false)
    private UbicacionVehiculo ubicacionVehiculo;
}
