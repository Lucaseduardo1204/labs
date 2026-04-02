# 🧪 Laboratório de Arquitetura Backend e JPA

Este repositório é um ambiente de provas de conceito (PoCs) e estudos práticos voltados para a Engenharia de Software no ecossistema Java, Spring Boot e Banco de Dados Relacional.

##Arquitetura de Sistemas Web (O Fluxo de Dados)

A construção de um sistema web backend robusto segue uma ordem de camadas rigorosa (Domain-Driven):

1 - Entities:
As entities são, basicamente, a representação do mundo real em nosso sistema, elas são representadas por classes com seus respectivos atributos e métodos. Ex: Usuário, Empresa, Conta Bancária, Animal
2 - Repositories
É a unica camada que tem a chave para o banco de dados: Aqui é onde mora o Spring Data JPA. Caso seja necessário salvar uma transação ou buscar o saldo do cliente no postgres, você pede para o Repository que é quem faz o sql por tráz dos panos
3 - DTO
ou Data Transfer Objects é o intermediário que filtra o que será exposto. Nunca devemos entregar nossa Entity completa direto para o cliente, portanto, é necessário utilizar um DTO o qual consiste em um objeto temporário que só carrega os dados que é seguro mostrar
4 - Services
O Service é onde ficam as regras de negócio. É no ContaService que é verificado se há saldo, se a conta está ativa ou bloqueada, onde ocorre também o cálculo de taxas. Nenhuma regra de negócio pode vazar daqui
5 - Controllers
Burros por design. Sua única função é ficar na porta da internet escutando requisições HTTP (GET, POST). Ele pega o pedido do cliente através do JSON, e transfere para o service processar, após o processamento o service devolve para o controller que devolve para o cliente com um StatusCode (200 ou 400). O Controller não calcula nada.

##Mapeamento Objeto-Relacional (JPA/Hibernate)

A anatomia de uma Entidade é dividida em três camadas de metadados (anotações):

* **Identidade da Classe:** @Entity = Indica que a classe é uma entidade, sem isso o Spring ignora a classe
                            @Table(name = “tb_empresas”) = Se não for indicado o nome o java cria a tabela com o nome da classe. Normalmente, tabelas são padronizadas no plural ou com prefixos.
* **Chave Primária (Primary Key): @Id = Indica que o atributo é a Primary Key
                                  @GeneratedValue(strategy = GenerationType.IDENTITY) = gera o valor automaticamente, no parâmetro strategy definimos o Tipo de geração para identidade.
* **Restrições de Coluna:** `@Column(nullable = false, unique = true, length = 13)` aplica a governança de dados e blindagem diretamente na infraestrutura, impedindo inserções inválidas.

## 🚀 Tecnologias Utilizadas
* Java 21 LTS
* Spring Boot (Spring Data JPA)
* PostgreSQL (via Docker)
* Padrões: POO, Fail-Fast, DTO
