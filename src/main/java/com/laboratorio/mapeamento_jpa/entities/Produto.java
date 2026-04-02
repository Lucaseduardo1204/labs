package com.laboratorio.mapeamento_jpa.entities;

//A presente classe tem por função realizar a fixação dos conhecimento estudados
// sobre arquitetura de entidades bem como anotações importantes do jakarta persistence


import jakarta.persistence.*;   //Pacote do JPA

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id     //Indica que o atributo é a Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)      //gera o valor automaticamente, no parâmetro strategy definimos o Tipo de geração para identidade.
    private Long id;

    @Column(nullable = false, unique = true, length = 13)   // Restrições de coluna, onde nao pode ser nula, deve ser unica e o tamanho deve ser de 13 caracteres
    private String codigoDeBarras;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private BigDecimal preco;


    //Após os atributos com suas respectivas anotações construiremos os
    // construtores cujo serão 2, um vazio pois o hibernate exige um construtor
    // vazio para funcionar por baixo dos panos, este pode ser como protected pra evitar manipulações em outras classes

    protected Produto(){}

    // e um de negócio onde será necessário parametrizar as informações como demonstrado abaixo

    public Produto(String codigoDeBarras, String nomeProduto, BigDecimal preco){
        this.codigoDeBarras = codigoDeBarras;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    // aqui continuaremos com métodos de classe normalmente


}
