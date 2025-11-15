
package com.mundomagico.api.model; // AJUSTE O PACOTE

import jakarta.persistence.*; 
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes_orcamento")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class SolicitacaoOrcamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    private String email;
    
    @Column(length = 1000)
    private String mensagem;
    
    private LocalDateTime dataSolicitacao = LocalDateTime.now();
    
    private boolean processado = false; 
}