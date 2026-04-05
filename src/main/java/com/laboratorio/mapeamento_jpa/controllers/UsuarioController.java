package com.laboratorio.mapeamento_jpa.controllers;

import com.laboratorio.mapeamento_jpa.dtos.UsuarioRequestDTO;
import com.laboratorio.mapeamento_jpa.dtos.UsuarioResponseDTO;
import com.laboratorio.mapeamento_jpa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @Valid @RequestBody UsuarioRequestDTO request
            ){
        UsuarioResponseDTO response = service.cadastrarUsuario(request);

        return ResponseEntity.status(201).body(response);
    }

    // O {id} é dinâmico. Se o cara acessar /api/usuarios/5, o Spring entende que o ID é 5.
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(

            // O @PathVariable "pesca" o número da URL e joga na variável Long id
            @PathVariable Long id) {

        // 1. Manda o Service trabalhar passando o id
        UsuarioResponseDTO response = service.buscarPorId(id);

        // 2. Retorna Status 200 (OK) com o JSON do usuário no corpo da resposta
        return ResponseEntity.ok(response);
    }


}
