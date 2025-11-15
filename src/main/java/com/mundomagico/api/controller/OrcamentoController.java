
package com.mundomagico.api.controller; // AJUSTE O PACOTE DE ACORDO COM SUA ESTRUTURA

import com.mundomagico.api.model.SolicitacaoOrcamento;
import com.mundomagico.api.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Diz ao Spring que esta classe lida com requisições REST
@RequestMapping("/api/orcamento") // Define a URL base: /api/orcamento
@CrossOrigin(origins = "*") // Permite o acesso do seu frontend em desenvolvimento
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    // Injeção de dependência do Service
    @Autowired
    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    // Mapeia o método POST que o formulário JS irá chamar
    @PostMapping
    public ResponseEntity<SolicitacaoOrcamento> receberSolicitacao(@RequestBody SolicitacaoOrcamento solicitacao) {
        
        // Chama a lógica de negócios para salvar a solicitação
        SolicitacaoOrcamento salva = orcamentoService.salvarSolicitacao(solicitacao);
        
        // Retorna HTTP 201 (Criado) indicando sucesso
        return new ResponseEntity<>(salva, HttpStatus.CREATED);
    }
}