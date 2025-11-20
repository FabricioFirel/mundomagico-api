package com.mundomagico.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Brinquedo brinquedo; // brinquedo escolhido

    private Integer quantidade;  // quantos iguais
    private Integer horas;       // por quantas horas será alugado
}
