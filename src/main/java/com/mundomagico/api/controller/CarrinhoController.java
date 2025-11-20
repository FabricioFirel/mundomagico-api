package com.mundomagico.api.controller;

import com.mundomagico.api.model.Carrinho;
import com.mundomagico.api.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping
    public Carrinho criarOuAtualizarCarrinho(@RequestBody Carrinho carrinho) {
        return carrinhoService.salvar(carrinho);
    }

    @GetMapping("/{id}")
    public Carrinho buscarCarrinho(@PathVariable Long id) {
        return carrinhoService.salvar(carrinhoService.buscar(id));
    }
}
