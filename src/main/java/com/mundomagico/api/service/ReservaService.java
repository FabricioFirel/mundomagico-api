package com.mundomagico.api.service;

import com.mundomagico.api.model.*;
import com.mundomagico.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    public Reserva criarReserva(Reserva reserva) {
        reserva.setStatus(StatusReserva.PENDENTE_PAGAMENTO);
        return reservaRepository.save(reserva);
    }

    public Reserva confirmarPagamento(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if (reserva.getStatus() != StatusReserva.PENDENTE_PAGAMENTO) {
            throw new RuntimeException("A reserva não está pendente de pagamento.");
        }

        // Baixar estoque após pagamento
        for (ReservaItem item : reserva.getItens()) {
            Brinquedo brinquedo = item.getBrinquedo();

            if (brinquedo.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para " + brinquedo.getNome());
            }

            brinquedo.setEstoque(brinquedo.getEstoque() - item.getQuantidade());
            brinquedoRepository.save(brinquedo);
        }

        reserva.setStatus(StatusReserva.PAGO);
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long id) {
        Reserva r = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        r.setStatus(StatusReserva.CANCELADO);
        reservaRepository.save(r);
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }
}
