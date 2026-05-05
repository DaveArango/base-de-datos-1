package com.proyecto.movibus.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "conductor")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Conductor {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Persona persona;

    @Column(name = "experiencia")
    private Integer experiencia;

    @ManyToOne
    @JoinColumn(name = "codigo_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToMany
    @JoinTable(
            name = "conductor_notificacion",
            joinColumns = @JoinColumn(name = "id_conductor"),
            inverseJoinColumns = @JoinColumn(name = "id_notificacion")
    )
    @JsonIgnore
    private Set<Notificacion> notificaciones;
}
