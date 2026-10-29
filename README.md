# Fruteira JDBC

Projeto acadêmico desenvolvido em Java para gerenciamento de produtos, clientes e vendas de uma fruteira, utilizando interface gráfica, autenticação de usuários e banco de dados PostgreSQL.

## Tecnologias utilizadas

- Java 21
- Java Swing
- JDBC
- PostgreSQL
- Apache Maven
- NetBeans IDE

## Banco de dados

O banco de dados `mercado` possui quatro tabelas principais:

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

### `usuario`

| Campo | Tipo |
|---|---|
| `id` | SERIAL (PK) |
| `nome` | VARCHAR(150) |
| `login` | VARCHAR(50) UNIQUE |
| `senha_hash` | CHAR(64) |

A tabela `usuario` armazena os usuários autorizados a acessar o sistema. A senha é armazenada como hash SHA-256, em vez de texto puro.

## Funcionalidades

- **Login:** autenticar usuários, validar campos obrigatórios e informar quando as credenciais são inválidas.
- **Produtos:** cadastrar, buscar, listar, atualizar e excluir.
- **Clientes:** cadastrar, buscar, listar, atualizar e excluir.
- **Vendas:** cadastrar, listar, excluir e calcular o valor total da venda.

## Organização do projeto

| Pacote | Responsabilidade |
|---|---|
| `conexao` | Conexão com o PostgreSQL |
| `entidades` | Representação dos dados de clientes, produtos, vendas e usuários |
| `produto` | Interface DAO e implementação JDBC de produtos |
| `cliente` | Interface DAO e implementação JDBC de clientes |
| `venda` | Interface DAO e implementação JDBC de vendas |
| `usuario` | Interface DAO e implementação JDBC para autenticação de usuários |
| `controladores` | Validação e coordenação das operações de usuários |
| `telas` | Interfaces gráficas do sistema, incluindo login e tela principal |

## Padrão DAO

O projeto utiliza o padrão **Data Access Object (DAO)** para separar as operações de banco de dados das interfaces gráficas.

- **Entidades:** representam os dados do sistema.
- **DAO:** define as operações disponíveis.
- **DAO JDBC:** implementa as operações com SQL e JDBC.
- **Controladores:** coordenam a lógica e a validação.
- **Telas:** permitem a interação do usuário com o sistema.

## Autenticação

A autenticação é realizada pela tela `TelaLogin`. O sistema valida os campos informados, consulta o usuário no PostgreSQL e compara o hash da senha digitada com o valor armazenado na tabela `usuario`.

Após a autenticação bem-sucedida, a tela principal é aberta. Caso as credenciais sejam inválidas, o sistema informa o usuário e permite uma nova tentativa.

## Como executar

1. Instale o JDK 21 e o PostgreSQL.
2. Crie o banco de dados `mercado` e as tabelas necessárias: `produtos`, `clientes`, `vendas` e `usuario`.
3. Configure as credenciais de conexão na classe `Conexao`.
4. Cadastre um usuário na tabela `usuario`, armazenando a senha como hash SHA-256.
5. Abra o projeto no NetBeans e aguarde o Maven carregar as dependências.
6. Configure a classe `TelaLogin` como classe inicial do projeto.
7. Execute o projeto e entre com as credenciais cadastradas.

## Objetivo acadêmico

Praticar programação orientada a objetos, interfaces gráficas, autenticação de usuários, acesso a banco de dados com JDBC, relacionamentos entre tabelas e aplicação do padrão DAO.

**Disciplina:** Programação Avançada — 2026B.
