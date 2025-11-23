package com.mundomagico.api.service;

import java.util.List;                  // modelo

import org.springframework.beans.factory.annotation.Autowired;  // repositório
import org.springframework.stereotype.Service;

import com.mundomagico.api.model.Brinquedo;
import com.mundomagico.api.repository.BrinquedoRepository;                                       // NonNull correto (Lombok)

import lombok.NonNull;

@Service
public class BrinquedoService {

    private final BrinquedoRepository brinquedoRepository;   // repositório

    @Autowired
    public BrinquedoService(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;      // injeta dependência
    }

    public Brinquedo salvarBrinquedo(@NonNull Brinquedo brinquedo) { 
        // @NonNull → garante que o brinquedo não seja nulo
        return brinquedoRepository.save(brinquedo);          // salva no banco
    }

    public List<Brinquedo> listarTodosBrinquedos() {
        return brinquedoRepository.findAll();                // retorna todos
    }

    public void excluirBrinquedo(@NonNull Long id) { 
        // @NonNull → id obrigatório
        brinquedoRepository.deleteById(id);                  // deleta do banco
    }
}
