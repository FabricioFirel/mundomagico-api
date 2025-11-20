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

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "brinquedo_id")
    private Brinquedo brinquedo;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}
