package com.mundomagico.api.service;

import java.util.Objects;                         // modelos

import org.springframework.beans.factory.annotation.Autowired;                   // repositórios
import org.springframework.stereotype.Service;

import com.mundomagico.api.model.Brinquedo;
import com.mundomagico.api.model.Carrinho;                                     // NonNull do Lombok
import com.mundomagico.api.model.ItemCarrinho;
import com.mundomagico.api.repository.BrinquedoRepository;
import com.mundomagico.api.repository.CarrinhoRepository;
import com.mundomagico.api.repository.ItemCarrinhoRepository;

import lombok.NonNull;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;         // repo carrinho

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository; // repo itens

    @Autowired
    private BrinquedoRepository brinquedoRepository;       // repo brinquedo

    public Carrinho salvar(@NonNull Carrinho carrinho) {   // carrinho obrigatório

        // Garante que a lista nunca seja nula
        Objects.requireNonNull(carrinho.getItens(), "Itens não podem ser nulos!");

        // Para cada item do carrinho
        for (ItemCarrinho item : carrinho.getItens()) {

            // Busca o brinquedo no banco
            Brinquedo brinquedoBD = brinquedoRepository.findById(item.getBrinquedo().getId())
                .orElseThrow(() -> new RuntimeException("Brinquedo não encontrado!"));

            // Verifica estoque
            if (brinquedoBD.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException(
                    "Estoque insuficiente para " + brinquedoBD.getNome() +
                    " (Disponível: " + brinquedoBD.getEstoque() + ")"
                );
            }

            item.setCarrinho(carrinho);                   // vincula item ao carrinho
        }

        carrinho.setTotal(calcularTotal(carrinho));        // calcula o total

        return carrinhoRepository.save(carrinho);          // salva no banco
    }

    // Calcula o valor total do carrinho
    private Double calcularTotal(@NonNull Carrinho carrinho) {
        return carrinho.getItens().stream()
            .mapToDouble(item ->
                item.getBrinquedo().getPrecoPorHora() *
                item.getHoras() *
                item.getQuantidade()
            )
            .sum();
    }

    // Busca um carrinho pelo id
    public Carrinho buscar(@NonNull Long id) {
        return carrinhoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }
}
