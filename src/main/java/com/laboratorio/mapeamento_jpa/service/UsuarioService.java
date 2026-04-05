package com.laboratorio.mapeamento_jpa.service;

import com.laboratorio.mapeamento_jpa.dtos.UsuarioRequestDTO;
import com.laboratorio.mapeamento_jpa.dtos.UsuarioResponseDTO;
import com.laboratorio.mapeamento_jpa.entities.Usuario;
import com.laboratorio.mapeamento_jpa.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO request){
        var usuarioExistente = repository.findByEmail(request.email());

        if (usuarioExistente.isPresent()){
            throw new RuntimeException("Usuário já cadastrado");
        }

        Usuario novoUsuario = new Usuario(request.nome(), request.email(), request.senha());

        repository.save(novoUsuario);

        return new UsuarioResponseDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public UsuarioResponseDTO buscarPorId(Long id){
        // 1. Busca no banco de dados. O Spring devolve a "caixa" Optional.
        // O findById já vem pronto de graça dentro do JpaRepository.
        var usuarioBox = repository.findById(id);

        // 2. Fail-Fast (A Validação)
        // Se a caixa estiver vazia, lançamos o erro. O código para de rodar aqui.
        if (usuarioBox.isEmpty()) {
            throw new RuntimeException("Erro: Usuário não encontrado com o ID " + id);
        }

        // 3. Extração
        // Se passou do IF acima, temos certeza que o usuário existe.
        // Usamos o .get() para tirar a Entidade de dentro da caixa.
        Usuario usuarioEncontrado = usuarioBox.get();

        // 4. Conversão e Retorno (A Blindagem)
        // Transformamos a Entidade em DTO, garantindo que a SENHA fique de fora.
        return new UsuarioResponseDTO(
                usuarioEncontrado.getId(),
                usuarioEncontrado.getNome(),
                usuarioEncontrado.getEmail()
        );

    }
}
