package com.mundomagico.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mundomagico.api.model.Carrinho;
import com.mundomagico.api.service.CarrinhoService;

@RestController
@RequestMapping("/api/carrinho")
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
        return carrinhoService.buscar(id);
    }
}
