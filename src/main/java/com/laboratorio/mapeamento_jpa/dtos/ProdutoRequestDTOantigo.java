package com.laboratorio.mapeamento_jpa.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

//Para que o jakarta validation funcione devemos adicionar sua dependencia no pom.xml e recarregar o maven
//   <dependency>
//			<groupId>org.springframework.boot</groupId>
//			<artifactId>spring-boot-starter-validation</artifactId>
//	</dependency>


// O RequestDTO é o que chega aqui pelo Front-end. Aqui fazemos as validações de segurança usando as anotações do
// Jakarta Validation. O pedido nem chega no Service se estiver inválido


public class ProdutoRequestDTOantigo {

    //ProdutroRequestDTOantigo pois se trata de uma classe normal como DTO, procedimento realizado em versões
    // anteriores do java, nas novas arquiteturas se utiliza records para tal função


    //@NotNull apenas impede que o valor seja null
    //@NotBlank  impede o null, impede string vazia "" e impede string só com espaços "   "

    //Para números e objetos = @NotNull
    //Para Textos = @NotBlank

    @NotBlank
    @Size(min = 13, max = 13, message = "O código de barras deve ter exatamente 13 digitos")
    private String codigoDeBarras;

    @NotBlank(message = "O nome do produto nao deve ser vazio")
    private String nomeProduto;

    @NotNull(message = "O preço é obrigatório" )
    @Positive(message = "O preço do produto deve ser maior que zero")
    private BigDecimal preco;


    public ProdutoRequestDTOantigo(String codigoDeBarras, String nomeProduto, BigDecimal preco){
        this.codigoDeBarras = codigoDeBarras;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public String getCodigoDeBarras(){
        return this.codigoDeBarras;
    }

    public String getNomeProduto(){
        return this.nomeProduto;
    }

    public BigDecimal getPreco(){
        return this.preco;
    }

}
