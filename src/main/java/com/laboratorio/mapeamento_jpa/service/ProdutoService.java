package com.laboratorio.mapeamento_jpa.service;


import com.laboratorio.mapeamento_jpa.dtos.ProdutoRequestDTO;
import com.laboratorio.mapeamento_jpa.dtos.ProdutoResponseDTO;
import com.laboratorio.mapeamento_jpa.entities.Produto;
import com.laboratorio.mapeamento_jpa.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    //Injeçao de dependencia: Precisamos definir uma constante(final) do repositorio para comunicação com o banco
    private final ProdutoRepository repository;

    //Aqui vai ser onde o Spring vai injetar o repositório aqui magicamente pelo construtor
    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    //Método principal o qual vai receber o DTO já configurado e devolver o DTO de resposta
    @Transactional //Se a energia cair um milissegundo depois do repository.save() ou der algum erro no meio do caminho,
    // de rollback em tudo e não deixe dados pela metade no banco
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO request){

// DESAFIO LÓGICO 1: Regra de Negócio (Fail-Fast)
// Use o 'repository.findByCodigoDeBarras(...)' para buscar se o produto já existe.
// Se existir (não for null), lance uma RuntimeException: "Erro: Produto já cadastrado!"

//        Passo a passo pra compreender
//        Passo 1 - Definir a variavel Produto existente que será o resultado da request.codigoDeBarras, em seguida a utilizaremos
//        como parametro pra função findByCodigoDeBarras que se encontra dentro da variavel repository
        var produtoExistente = repository.findByCodigoDeBarras(request.codigoDeBarras());

//        Passo 2 -  Utilizaremos a variavel produtoExistente no if para que podemos comparar se ela é != null ou
//        caso seja utilizado o Optional, utilizaremos o método .isPresent

        if (produtoExistente.isPresent()){
            throw new RuntimeException("Erro: Produto já cadastrado");
        }

// DESAFIO LÓGICO 2: Conversão (De DTO para Entity)
// Crie um 'new Produto(...)' passando os dados que vieram do 'request'.

//        Para criar um novo produto criamos a variavel novoProduto do tipo Produto e instanciamos um new Produto passando a request
//        com os respectivos atributos para os parametros necessários
        Produto novoProduto = new Produto(request.codigoDeBarras(), request.nomeProduto(), request.preco());

// DESAFIO LÓGICO 3: Persistência
// Salve a entidade no banco usando: repository.save(novoProduto);
        repository.save(novoProduto);

// DESAFIO LÓGICO 4: O Retorno
// Pegue o produto que foi salvo, converta em um 'new ProdutoResponseDTO(...)' e dê o return.

        //Para isso ja podemos retornar um new ProdutoResponseDTO juntamente com os atributos da ENTIDADE SALVA
        return new ProdutoResponseDTO(novoProduto.getCodigoDeBarras(), novoProduto.getNomeProduto(), novoProduto.getPreco());
    }





}
