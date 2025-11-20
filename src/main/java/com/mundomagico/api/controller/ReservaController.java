package com.mundomagico.api.controller;

import com.mundomagico.api.model.Reserva;
import com.mundomagico.api.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.criarReserva(reserva), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/confirmar-pagamento")
    public ResponseEntity<Reserva> confirmarPagamento(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.confirmarPagamento(id));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }
}
