package com.proyecto.movibus.backend.entidades;

import com.proyecto.movibus.backend.entidades.ids.RecorridoIncidenteId;
import jakarta.persistence.*;

@Entity
@Table(name = "recorrido_incidente")
public class RecorridoIncidente {
    @EmbeddedId
    private RecorridoIncidenteId id;

    @ManyToOne
    @MapsId("codigo_recorrido")
    @JoinColumn(name = "codigo_recorrido")
    private Recorrido recorrido;

    @ManyToOne
    @MapsId("codigo_incidente")
    @JoinColumn(name = "codigo_incidente")
    private Incidente incidente;

    @ManyToOne
    @JoinColumn(name = "codigo_vehiculo", nullable = false)
    private Vehiculo vehiculo;
}
