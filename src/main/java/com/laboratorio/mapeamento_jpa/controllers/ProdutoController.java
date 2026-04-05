package com.laboratorio.mapeamento_jpa.controllers;

import com.laboratorio.mapeamento_jpa.dtos.ProdutoRequestDTO;
import com.laboratorio.mapeamento_jpa.dtos.ProdutoResponseDTO;
import com.laboratorio.mapeamento_jpa.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    ///Para entender a injeção de dependencias precisamos pensar como seria feito no java 'puro' no qual precisaríamos utilizar o
    /// new na instanciação do objeto. Exemplo:
    /// private PodutoServide = new ProdutoService();
    ///  O que não pode mais acontecer pois quando fazemos isso o é criado um acoplamento muito forte no qual o controller passa a
    /// ser 'dono' do service, onde o Service necessita do repository, ao utilizar o 'new' o controller também teria que saber criar
    /// o Repository que precisaria da conexão com o banco, etc. Com isso o Controller teria de fazer o sistema todo
    ///Para evitar esse problema aqui é necessário fazer uma *INVERSÃO DE CONTROLE* da qual tiramos a responsabilidade de criar o objeto
    /// da mão do controller e entregamos ao Spring Boot

    //Aqui declaramos essa constante como uma necessidade, "O controller precisa do service"
    private final ProdutoService service;


    ///Aqui é feito um construtor. A regra do java diz que, como declaramos a variável como constante(final), é obrigatório que a mesma
    ///seja preenchida no momento do nascimento da classe. Nunca chamaremos esse construtor, quando rodarmos a aplicacao o próprio Spring
    /// faz o uso desse recurso para fazer as criações necessárias da seguinte maneira:
    /// Vê o @RestController e tenta cria-lo porém para criá-lo é necessario um Produto service
    /// Tenta criar o ProdutoService porém o Service precisa de um produtoRepository
    /// Vai até o ProdutoRepository e conecta com o PostgresSQL, cria o objeto Repository e entrega para o Service
    /// Com o Service pronto, o Spring pega-o e injeeta no construtor do controller
    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    //Verbos de criação
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar (
            //Interceptador com validações de segurança
            @Valid @RequestBody ProdutoRequestDTO request){

            //Pede para o service cadastrar o produto
        ProdutoResponseDTO response = service.cadastrarProduto(request);

        //Envelopa a resposta com o Status 201 e JSON
        return ResponseEntity.status(201).body(response);

    }



}
