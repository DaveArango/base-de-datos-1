package com.proyecto.movibus.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "recorrido")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Recorrido {
    @Id
    private Integer id;

    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @ManyToOne
    @JoinColumn(name = "codigo_estado_recorrido", nullable = false)
    private EstadoRecorrido estadoRecorrido;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion_recorrido", nullable = false)
    private UbicacionRecorrido ubicacionRecorrido;

    @ManyToMany
    @JoinTable(
            name = "recorrido_vehiculo",
            joinColumns = @JoinColumn(name = "id_recorrido"),
            inverseJoinColumns = @JoinColumn(name = "codigo_vehiculo")
    )
    @JsonIgnore
    private Set<Vehiculo> vehiculos;
}
