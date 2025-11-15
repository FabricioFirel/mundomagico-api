
package com.mundomagico.api.repository; // AJUSTE O PACOTE 
import com.mundomagico.api.model.SolicitacaoOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<SolicitacaoOrcamento, Long> {
    // O Spring faz todo o trabalho de salvar e buscar aqui!
}
