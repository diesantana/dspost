# DSPost (MongoDB)
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/diesantana/dspost/blob/main/LICENSE)
## Visão Geral
DSPost é uma API REST desenvolvida em Java com o framework Spring Boot, que utiliza MongoDB como banco de dados. O objetivo do projeto é fornecer uma plataforma para gerenciamento de posts e usuários, permitindo realizar operações de CRUD (Create, Read, Update, Delete) em ambos.

## Modelo de Domínio
O modelo de domínio é composto por duas entidades principais: `User` e `Post`, organizadas em um modelo de agregação.

### Modelo de Agregação
No contexto do MongoDB, utilizamos o conceito de agregação para organizar dados relacionados de forma que eles possam ser acessados de maneira eficiente.

- **User**: Representa um usuário do sistema e contém os seguintes campos:
  - `id`: Identificador único do usuário.
  - `name`: Nome do usuário.
  - `email`: Email do usuário.
  - `posts`: Lista de posts associados ao usuário. Esta lista armazena referências aos posts criados pelo usuário, estabelecendo a relação entre as entidades `User` e `Post`.

- **Post**: Representa um post criado por um usuário e contém os seguintes campos:
  - `id`: Identificador único do post.
  - `moment`: Momento da criação do post.
  - `title`: Título do post.
  - `body`: Corpo do post.
  - `author`: Autor do post, representado por um objeto `Author`. Este objeto é uma versão simplificada da entidade `User`, contendo apenas os campos necessários para identificar o autor.
  - `comments`: Lista de comentários associados ao post. Cada comentário é representado por um objeto `Comment`, que contém informações sobre o autor e o texto do comentário.

### Objetos Embutidos
Os objetos embutidos são utilizados para representar subdocumentos dentro das entidades principais:

- **Author**: Contém os campos `id` e `name`, representando o autor de um post ou comentário.
- **Comment**: Contém os campos `text`, `moment` e `author`, representando um comentário feito em um post.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.0**
- **Spring Data MongoDB**
- **MongoDB**
- **Maven**
- **Postman** (para testes de API)

## Motivos para Escolher o MongoDB
1. **Escalabilidade Horizontal**: MongoDB oferece escalabilidade horizontal, facilitando o crescimento e a expansão do banco de dados sem grandes complicações.
2. **Flexibilidade no Modelo de Dados**: O modelo de documento do MongoDB permite uma flexibilidade maior para armazenar dados semi-estruturados, facilitando mudanças no modelo sem grandes migrações.
3. **Desempenho**: Para aplicações que lidam com grandes volumes de dados e operações de leitura/escrita intensivas, MongoDB oferece um desempenho superior devido à sua arquitetura sem esquema.
4. **Fácil Integração com Java**: A integração com Spring Data MongoDB simplifica a manipulação dos dados, utilizando abstrações que facilitam o desenvolvimento.

## Pré-requisitos
- Java 17 instalado
- Maven instalado
- MongoDB instalado e em execução na porta padrão (27017)

## Tutorial de Instalação
1. **Instalar Java 17**:
   - Baixe e instale o Java 17 a partir do [site oficial](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

2. **Instalar Maven**:
   - Baixe e instale o Maven a partir do [site oficial](https://maven.apache.org/download.cgi).
   - Configure a variável de ambiente `MAVEN_HOME` apontando para o diretório de instalação do Maven.

3. **Instalar MongoDB**:
   - Baixe e instale o MongoDB a partir do [site oficial](https://www.mongodb.com/try/download/community).

4. **Instalar Postman**:
   - Baixe e instale o Postman a partir do [site oficial](https://www.postman.com/downloads/).

## Como Executar o Projeto
1. Certifique-se de que o MongoDB está rodando:
   - Inicie o serviço do MongoDB no seu sistema operacional. O MongoDB deve estar em execução na porta padrão (27017).

2. Clone o repositório:
   ```bash
   git clone git@github.com:diesantana/dspost.git  
   cd dspost
   ```

3. Compile o projeto e baixe as dependências:
   ```bash
   mvn clean install
   ```

4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

5. Acesse a aplicação:
   - A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API
Aqui estão os endpoints disponíveis para interação com a API. Você pode utilizar o [Postman](https://www.postman.com/downloads/) para testar os endpoints.  
- Você encontra a coleção Postman do projeto [aqui](https://github.com/diesantana/dspost/blob/main/DSPosts.postman_collection.json).

### Endpoints de Usuários

- **Listar todos os usuários**
  - **Método**: GET
  - **URL**: `/users`

- **Obter usuário por ID**
  - **Método**: GET
  - **URL**: `/users/{id}`

- **Listar posts de um usuário**
  - **Método**: GET
  - **URL**: `/users/{id}/posts`

- **Criar um novo usuário**
  - **Método**: POST
  - **URL**: `/users`
  - **Corpo da Requisição**:
    ```json
    {
      "name": "Ana Red",
      "email": "ana@gmail.com"
    }
    ```

- **Atualizar um usuário**
  - **Método**: PUT
  - **URL**: `/users/{id}`
  - **Corpo da Requisição**:
    ```json
    {
      "name": "Maria Atualizada",
      "email": "maria2@gmail.com"
    }
    ```

- **Excluir um usuário**
  - **Método**: DELETE
  - **URL**: `/users/{id}`

### Endpoints de Posts

- **Obter post por ID**
  - **Método**: GET
  - **URL**: `/posts/{id}`

- **Buscar posts por título**
  - **Método**: GET
  - **URL**: `/posts/titlesearch`
  - **Parâmetros de Query**:
    - `text`: Texto a ser buscado no título

- **Busca completa de posts**
  - **Método**: GET
  - **URL**: `/posts/fullsearch`
  - **Parâmetros de Query**:
    - `text`: Texto a ser buscado
    - `start`: Data de início (ISO 8601)
    - `end`: Data de término (ISO 8601)

## Estrutura e Padrões Utilizados
O projeto segue uma estrutura bem organizada utilizando os padrões de projeto do Spring Boot.

- **Controllers**: Controladores responsáveis por lidar com as requisições HTTP e direcioná-las aos serviços apropriados.
- **Services**: Classes de serviço que contêm a lógica de negócios e interagem com os repositórios.
- **Repositories**: Interfaces que estendem `MongoRepository` para interagir com o MongoDB.
- **Models**: Classes que representam as entidades do sistema.
  - `entities`: Entidades principais (`User`, `Post`).
  - `embedded`: Objetos incorporados (`Author`, `Comment`).
  - `dto`: Objetos de Transferência de Dados (`UserDTO`, `PostDTO`, `ErrorDetails`).

## Autor
Diego Santana
- LinkedIn: [Diego santana](https://linkedin.com/in/seu-linkedin)
- WhatsApp: +55 11 949 890 078
- Email: 3c.santana@gmail.com
