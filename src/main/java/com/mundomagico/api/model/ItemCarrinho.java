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
    @JoinColumn(name = "brinquedo_id")
    private Brinquedo brinquedo;

    private Integer quantidade;
    private Integer horas;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;
}
