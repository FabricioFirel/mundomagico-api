package com.mundomagico.api.model; 
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"brinquedos\"")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Brinquedo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Column(columnDefinition = "TEXT") 
    private String descricao;
    
    private Double precoPorHora;
    
    private Integer estoque; // Quantidade disponível

    private String categoria;
    
    private String imagemUrl; // URL da imagem do brinquedo
}