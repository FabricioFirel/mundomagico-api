package com.mundomagico.api.model; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brinquedos")
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