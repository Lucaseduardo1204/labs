package com.laboratorio.mapeamento_jpa.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank
        @Size(min = 13, max = 13)
        String codigoDeBarras,

        @NotBlank
        String nomeProduto,

        @NotNull
        @Positive
        BigDecimal preco
) {}
