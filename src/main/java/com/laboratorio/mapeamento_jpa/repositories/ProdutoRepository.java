package com.laboratorio.mapeamento_jpa.repositories;

import com.laboratorio.mapeamento_jpa.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//indicamos que a interface herda da interface JpaRepository e dentro das <> passamos como parâmetro a entidade e o tipo do id
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    Optional<Produto> findByCodigoDeBarras(String codigoDeBarras);
    // Opcional: "Pode ser que tenha um Produto aqui dentro, pode ser que não".


//    O Spring Data usa a nomenclatura do método para montar o SQL. Basta começar o nome do método com findBy seguido do
//     nome exato do seu atributo (com a primeira letra maiúscula
}
