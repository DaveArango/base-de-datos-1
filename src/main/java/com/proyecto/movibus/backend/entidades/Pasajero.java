package com.proyecto.movibus.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "pasajero")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pasajero {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "codigo_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_tarifa", nullable = false)
    private Tarifa tarifa;

    @ManyToMany
    @JoinTable(
            name = "pasajero_notificacion",
            joinColumns = @JoinColumn(name = "id_pasajero"),
            inverseJoinColumns = @JoinColumn(name= "id_notificacion")
    )
    @JsonIgnore
    private Set<Notificacion> notificaciones;
}
