package com.laboratorio.mapeamento_jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String senha;

    protected Usuario(){}

    public Usuario (String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
