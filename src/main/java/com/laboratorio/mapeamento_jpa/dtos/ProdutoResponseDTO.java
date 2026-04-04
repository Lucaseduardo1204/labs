package com.laboratorio.mapeamento_jpa.dtos;

import java.math.BigDecimal;

public record ProdutoResponseDTO(String codigoDeBarras, String nomeProduto, BigDecimal preco) {

    //O Java já gera os getters e o construtor invisivelmente
}
