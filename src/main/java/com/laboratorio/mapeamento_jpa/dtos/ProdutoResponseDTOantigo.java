package com.laboratorio.mapeamento_jpa.dtos;


// O ResponseDTO é o que devolvemos para o front-end. Ele só tem os dados que a tela precisa exibir.
// Não tem regras de validação aqui, pois o dado já está seguro vindo do banco de dados.

import java.math.BigDecimal;

public class ProdutoResponseDTOantigo {
    private String codigoDeBarras;
    private String nomeProduto;
    private BigDecimal preco;

    public ProdutoResponseDTOantigo(String codigoDeBarras, String nomeProduto, BigDecimal preco){
        this.codigoDeBarras = codigoDeBarras;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
