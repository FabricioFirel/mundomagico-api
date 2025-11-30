package com.mundomagico.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserva_itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "brinquedo_id")
    private Brinquedo brinquedo;

    private Integer quantidade;
    private Integer horas;
}
