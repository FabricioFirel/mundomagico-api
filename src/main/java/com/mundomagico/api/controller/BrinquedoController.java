package com.mundomagico.api.controller;

import com.mundomagico.api.model.Brinquedo;
import com.mundomagico.api.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"https://fabriciofirel.github.io", "http://127.0.0.1:5500"}) // Permite acesso de qualquer frontend (como o Live Server)
@RestController
@RequestMapping("/api/brinquedos")
public class BrinquedoController {

    private final BrinquedoService brinquedoService;

    @Autowired
    public BrinquedoController(BrinquedoService brinquedoService) {
        this.brinquedoService = brinquedoService;
    }

    // Endpoint para adicionar um novo brinquedo (POST)
    @PostMapping
    public ResponseEntity<Brinquedo> adicionarBrinquedo(@RequestBody Brinquedo brinquedo) {
        Brinquedo novoBrinquedo = brinquedoService.salvarBrinquedo(brinquedo);
        return new ResponseEntity<>(novoBrinquedo, HttpStatus.CREATED); // Retorna status 201
    }

    // Endpoint para listar todos os brinquedos (GET)
    @GetMapping
    public ResponseEntity<List<Brinquedo>> listarBrinquedos() {
        List<Brinquedo> brinquedos = brinquedoService.listarTodosBrinquedos();
        return new ResponseEntity<>(brinquedos, HttpStatus.OK); // Retorna status 200
    }

    

}// 