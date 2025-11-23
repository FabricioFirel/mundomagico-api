package com.mundomagico.api.service;

import java.util.Objects;                       // modelos

import org.springframework.beans.factory.annotation.Autowired;                 // repositórios
import org.springframework.stereotype.Service;

import com.mundomagico.api.model.Brinquedo;
import com.mundomagico.api.model.Reserva;                                     // NonNull do Lombok
import com.mundomagico.api.model.ReservaItem;
import com.mundomagico.api.model.StatusReserva;
import com.mundomagico.api.repository.BrinquedoRepository;
import com.mundomagico.api.repository.ReservaRepository;

import lombok.NonNull;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;           // repo reserva

    @Autowired
    private BrinquedoRepository brinquedoRepository;       // repo brinquedo

    // Cria uma nova reserva
    public Reserva criarReserva(@NonNull Reserva reserva) {
        Objects.requireNonNull(reserva.getItens(), "Itens da reserva não podem ser nulos");

        reserva.setStatus(StatusReserva.PENDENTE_PAGAMENTO);  // define status inicial
        return reservaRepository.save(reserva);                // salva
    }

    // Confirma pagamento e atualiza estoque
    public Reserva confirmarPagamento(@NonNull Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        // Só permite confirmação se ainda estiver pendente
        if (reserva.getStatus() != StatusReserva.PENDENTE_PAGAMENTO) {
            throw new RuntimeException("A reserva não está pendente de pagamento.");
        }

        Objects.requireNonNull(reserva.getItens(), "Itens da reserva não podem ser nulos");

        // Reduz o estoque para cada item
        for (ReservaItem item : reserva.getItens()) {

            Brinquedo brinquedo = brinquedoRepository.findById(item.getBrinquedo().getId())
                    .orElseThrow(() -> new RuntimeException("Brinquedo não encontrado"));

            if (brinquedo.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para " + brinquedo.getNome());
            }

            brinquedo.setEstoque(brinquedo.getEstoque() - item.getQuantidade()); // baixa estoque
            brinquedoRepository.save(brinquedo);                                 // atualiza
        }

        reserva.setStatus(StatusReserva.PAGO);            // altera status
        return reservaRepository.save(reserva);           // salva novamente
    }

    // Cancela uma reserva
    public void cancelarReserva(@NonNull Long id) {
        Reserva r = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        r.setStatus(StatusReserva.CANCELADO);
        reservaRepository.save(r);
    }

    // Busca reserva pelo ID
    public Reserva buscarPorId(@NonNull Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }
}
