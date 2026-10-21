# Fruteira JDBC

Projeto acadêmico desenvolvido em Java para gerenciamento de produtos, clientes e vendas de uma fruteira, utilizando interface gráfica e banco de dados PostgreSQL.

## Tecnologias utilizadas

- Java 21
- Java Swing
- JDBC
- PostgreSQL
- Apache Maven
- NetBeans IDE

## Banco de dados

O banco de dados possui três tabelas principais:

### `produtos`

| Campo | Tipo |
|---|---|
| `id` | SERIAL (PK) |
| `nome` | VARCHAR(100) |
| `preco` | NUMERIC(10,2) |

### `clientes`

| Campo | Tipo |
|---|---|
| `id` | SERIAL (PK) |
| `nome` | VARCHAR(100) |
| `cpf` | VARCHAR(14) |

### `vendas`

| Campo | Tipo |
|---|---|
| `id` | SERIAL (PK) |
| `cliente_id` | INTEGER (FK) |
| `produto_id` | INTEGER (FK) |
| `quantidade` | INTEGER |

**Relacionamentos:** cada venda está associada a um cliente e a um produto por meio de chaves estrangeiras.

## Funcionalidades

- **Produtos:** cadastrar, buscar, listar, atualizar e excluir.
- **Clientes:** cadastrar, buscar, listar, atualizar e excluir.
- **Vendas:** cadastrar, listar, excluir e calcular o valor total da venda.

## Organização do projeto

| Pacote | Responsabilidade |
|---|---|
| `conexao` | Conexão com o PostgreSQL |
| `produto` | Modelo, interface DAO e implementação JDBC de produtos |
| `cliente` | Modelo, interface DAO e implementação JDBC de clientes |
| `venda` | Modelo, interface DAO e implementação JDBC de vendas |
| `telas` | Interfaces gráficas do sistema |

## Padrão DAO

O projeto utiliza o padrão **Data Access Object (DAO)** para separar as operações de banco de dados das interfaces gráficas.

- **Model:** representa os dados.
- **DAO:** define as operações disponíveis.
- **DAO JDBC:** implementa as operações com SQL e JDBC.
- **Telas:** permitem a interação do usuário com o sistema.

## Como executar

1. Instale o JDK 21 e o PostgreSQL.
2. Crie o banco de dados `mercado` e as tabelas necessárias.
3. Configure as credenciais na classe `Conexao`.
4. Abra o projeto no NetBeans e aguarde o Maven carregar as dependências.
5. Execute a classe principal `TelaPrincipal`.

## Objetivo acadêmico

Praticar programação orientada a objetos, interfaces gráficas, acesso a banco de dados com JDBC, relacionamentos entre tabelas e aplicação do padrão DAO.

**Disciplina:** Programação Avançada — 2026B.
