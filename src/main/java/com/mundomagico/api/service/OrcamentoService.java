

package com.mundomagico.api.service; // AJUSTE O PACOTE 

import com.mundomagico.api.model.SolicitacaoOrcamento;
import com.mundomagico.api.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;

    @Autowired
    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public SolicitacaoOrcamento salvarSolicitacao(SolicitacaoOrcamento solicitacao) {
        System.out.println("LOG: Solicitacao de Orçamento recebida de: " + solicitacao.getEmail() + " e será salva.");
        return orcamentoRepository.save(solicitacao); 
    }
}