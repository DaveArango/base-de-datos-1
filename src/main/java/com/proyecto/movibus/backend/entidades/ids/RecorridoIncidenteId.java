package com.proyecto.movibus.backend.entidades.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecorridoIncidenteId implements Serializable {
    @Column(name = "codigo_recorrido")
    private int codigo_recorrido;

    @Column(name = "codigo_incidente")
    private int codigo_incidente;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecorridoIncidenteId that = (RecorridoIncidenteId) o;
        return Objects.equals(codigo_recorrido, that.codigo_recorrido) && Objects.equals(codigo_incidente, that.codigo_incidente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_recorrido, codigo_incidente);
    }
}
