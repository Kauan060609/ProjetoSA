# WE-COMMERCE (ProjetoSA)

Sistema de back-end em **Java** para gerenciamento de um e-commerce, com persistência em **MySQL**. O projeto simula as operações internas de uma loja virtual: cadastro de clientes, endereços, categorias, fornecedores, produtos, estoque e compras, além de consultas analíticas (queries e views) para apoiar decisões de negócio como faturamento, estoque e comportamento de clientes.

> Apresentação do projeto (protótipo de site/pitch): [WE-COMMERCE no Figma Make](https://www.figma.com/make/8MFTbWnQMPIQx9FOFdteqA/Site-para-apresenta%C3%A7%C3%A3o-WE-COMMERCE)

## Índice

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Modelo de dados](#modelo-de-dados)
- [Estrutura de pastas](#estrutura-de-pastas)
- [Como executar](#como-executar)
- [Segurança e configuração do banco](#segurança-e-configuração-do-banco)
- [Roadmap / melhorias futuras](#roadmap--melhorias-futuras)

## Sobre o projeto

O **ProjetoSA** é uma aplicação de console em Java que implementa a camada de dados e regras de negócio de um e-commerce fictício (WE-COMMERCE). O sistema segue o padrão **DAO (Data Access Object)**, separando claramente:

- **`model`** – entidades do domínio (Cliente, Produto, Compra, etc.)
- **`DAO`** – classes responsáveis pelo acesso e manipulação dos dados no MySQL
- **`exceptions`** – exceções customizadas para validação de regras de negócio
- **`connection`** – gerenciamento da conexão com o banco de dados

A navegação é feita por um menu interativo no terminal (`Main.java`), que permite operar cada tabela do sistema e também executar consultas (queries) e visualizações (views) pré-definidas.

## Funcionalidades

- **CRUD completo** para as entidades: Categoria, Cliente, Compra, Endereço, Estoque, Fornecedor, Produto e ProdutoCompra
- **Validações de negócio** via exceções customizadas:
  - CPF inválido
  - Telefone inválido
  - CEP inválido
  - Senha inválida
  - Valor inválido
- **Views SQL** para relatórios rápidos, entre elas:
  - Alerta de estoque abaixo do mínimo
  - Perfil e perfil público do cliente
  - Clientes VIP (maior valor gasto)
  - Faturamento por marca
  - Concentração de clientes por cidade/estado
- **Queries analíticas** organizadas por tema:
  - Gestão e perfil de clientes
  - Localização e geografia das vendas
  - Estoque e fornecedores
  - Faturamento, ticket médio e auditoria de preços
  - Desempenho de categorias
  - Análise de produtos e itens de pedido (Top 5 mais vendidos, busca por marca, etc.)

## Tecnologias

- **Java** (JDK)
- **JDBC** com [MySQL Connector/J 9.7.0](https://dev.mysql.com/downloads/connector/j/)
- **MySQL** (hospedado na nuvem via [Aiven](https://aiven.io/))
- **VS Code** com a extensão *Java Extension Pack*

## Modelo de dados

O banco `projeto_sa` é composto pelas seguintes tabelas principais: `endereco`, `cliente`, `compra`, `categoria`, `estoque`, `fornecedor`, `produto` e `produto_compra` (tabela associativa entre `compra` e `produto`).

O Diagrama de Entidade-Relacionamento (DER) completo está disponível em [`database/der`](./database/der).

Scripts SQL disponíveis em [`database`](./database):

| Arquivo | Descrição |
|---|---|
| `database.sql` | Criação do banco de dados |
| `tables.sql` | Criação das tabelas e relacionamentos |
| `inserts.sql` | Inserções de dados de exemplo |
| `views.sql` | Views para relatórios |
| `querys.sql` | Consultas analíticas (29 queries) |
| `users.sql` | Usuários e permissões do banco |

## Estrutura de pastas

```
ProjetoSA/
├── database/
│   ├── der/             # Diagrama entidade-relacionamento
│   ├── database.sql
│   ├── tables.sql
│   ├── inserts.sql
│   ├── views.sql
│   ├── querys.sql
│   └── users.sql
├── src/
│   ├── DAO/              # Acesso a dados (CRUD, views e queries)
│   ├── model/            # Entidades do domínio
│   ├── exceptions/       # Exceções de validação
│   ├── connection/       # Conexão com o MySQL
│   ├── lib/               # Dependências (mysql-connector-j)
│   └── Main.java          # Menu principal / ponto de entrada
├── bin/                   # Saída compilada (.class)
└── .vscode/                # Configurações do VS Code
```

## Como executar

### Pré-requisitos

- JDK instalado (11+)
- VS Code com a extensão **Extension Pack for Java** (ou outra IDE Java de sua preferência)
- Acesso a uma instância MySQL (local ou na nuvem)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/Kauan060609/ProjetoSA.git
   cd ProjetoSA
   ```
2. Configure a conexão com o banco em `src/connection/ConnectionSA.java` (veja a seção [Segurança e configuração do banco](#segurança-e-configuração-do-banco) antes de subir qualquer credencial).
3. Execute os scripts em [`database`](./database) na ordem: `database.sql` → `tables.sql` → `inserts.sql` → `views.sql`.
4. Compile e execute `src/Main.java` pela IDE (view **JAVA PROJECTS** do VS Code) ou via terminal:
   ```bash
   javac -cp src/lib/mysql-connector-j-9.7.0.jar -d bin src/Main.java src/**/*.java
   java -cp bin:src/lib/mysql-connector-j-9.7.0.jar Main
   ```
5. Use o menu interativo no terminal para navegar entre tabelas, views e queries.

## Segurança e configuração do banco

⚠️ **Atenção:** atualmente as credenciais de acesso ao MySQL (usuário, senha, host) estão fixas diretamente em `src/connection/ConnectionSA.java`. Isso **não é recomendado**, principalmente em repositórios públicos.

Sugestões de melhoria:

- Mover usuário, senha e URL para **variáveis de ambiente** ou um arquivo `.env`/`config.properties` que **não** seja versionado (adicionar ao `.gitignore`).
- Trocar a senha atual do banco, já que foi exposta no histórico do repositório.
- Utilizar um arquivo de exemplo (`config.properties.example`) com placeholders para orientar outros desenvolvedores.

## Roadmap / melhorias futuras

- [ ] Externalizar credenciais do banco (variáveis de ambiente)
- [ ] Adicionar testes automatizados para DAOs e exceções
- [ ] Interface gráfica ou API REST para substituir o menu de terminal
- [ ] Documentação das queries e views com exemplos de saída
- [ ] Integração com o front-end apresentado no protótipo Figma (WE-COMMERCE)

---

Desenvolvido como projeto acadêmico/prático de banco de dados e Java (DAO pattern).
