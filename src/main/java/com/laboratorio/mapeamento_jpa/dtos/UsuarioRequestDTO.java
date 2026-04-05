package com.laboratorio.mapeamento_jpa.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {
}
